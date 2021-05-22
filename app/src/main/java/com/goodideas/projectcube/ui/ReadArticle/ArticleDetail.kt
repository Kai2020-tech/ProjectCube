package com.goodideas.projectcube.ui.ReadArticle

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.SharedElementCallback
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.hideKeyboard
import com.goodideas.projectcube.databinding.FragmentArticleDetailBinding
import com.goodideas.projectcube.ui.Login.userId
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

const val imagePrefix = "http://api.rrrui.site/storage/"

class ArticleDetail : Fragment() {
    lateinit var binding:FragmentArticleDetailBinding
    lateinit var sadapter: CommentAdapter
    lateinit var rv:RecyclerView
    private var articleId:Int? = null

    private val vm by viewModel<ArticleDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_article_detail, container, false)

        articleId = arguments?.getInt("articleId") ?: Int.MIN_VALUE
        Timber.d(articleId.toString())
        if (articleId == Int.MIN_VALUE){
            Toast.makeText(this.requireContext(), "article mismatch, please try again",Toast.LENGTH_SHORT).show()
        }
        else vm.getSinglePost(articleId!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObserver()

    }
    private fun initObserver(){
        vm.singlePostContent.observe(viewLifecycleOwner, Observer {
            binding.title.text = it.post?.title
            binding.content.text = it.post?.content
            if (it.post?.image != "null"){
                val image = imagePrefix + it.post?.image
                Glide.with(this.requireActivity())
                    .load(image)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(binding.articleFirstImage)
            }

            if (it.comments?.isNullOrEmpty() == true)Toast.makeText(this.requireContext(),"No comment",Toast.LENGTH_SHORT).show()
            sadapter.submitList(it.comments)
        })

    }
    private fun initUI(){
        binding.commentEdit.paint.flags = Paint.UNDERLINE_TEXT_FLAG

        sadapter = CommentAdapter()
        rv = binding.articleComment
        rv.apply {
            layoutManager =LinearLayoutManager(this@ArticleDetail.requireContext())
            adapter = sadapter
            addItemDecoration(
                DividerItemDecoration(this@ArticleDetail.requireContext(),
                    DividerItemDecoration.VERTICAL)
            )
        }

        binding.more.setOnClickListener {
            Timber.d("more")
            val authorArticle = arrayOf("report","edit", "delete")
            val notAuthorArticle = arrayOf("report")

            MaterialAlertDialogBuilder(this.requireContext())
                .setItems(
                    if(vm.singlePostContent.value?.post?.userId == userId) authorArticle
                    else notAuthorArticle
                ) { dialog, which ->
                    Timber.d("more")
                    when(which){
                        0 -> Toast.makeText(this.requireContext(),"report $which", Toast.LENGTH_SHORT).show()
                        1 -> findNavController().navigate(R.id.action_articleDetail_to_createPostFragment, bundleOf("editArticle" to vm.singlePostContent.value))
                        else -> {
                            // todo kai add delete method
                            //if success
                            findNavController().navigate(R.id.action_articleDetail_to_articleListFragment)
                            Timber.d("delete")
                        }
                    }
                }
                .show()
        }

        binding.commentEdit.setOnClickListener {
            val dialogView = this.requireActivity().layoutInflater.inflate(R.layout.comment_layout,null)
            val query = dialogView.findViewById<EditText>(R.id.comment_write_dialog_edittext)
            AlertDialog.Builder(this.requireContext())
                .setTitle("comment")
                .setPositiveButton("sent"){ _,_->
                    if (articleId == Int.MIN_VALUE || query.text.isNullOrBlank()){
                        Toast.makeText(this.requireContext(),
                            "invalid request, check comment content or article status",
                        Toast.LENGTH_SHORT).show()
                    } else {
                        vm.createCommit(articleId!!, query.text.toString())
                        //todo kai if success vm.getSinglePost(articleId!!)
                        hideKeyboard(it)
                    }
                }
                .setView(dialogView)
                .show()
        }
    }
}