package com.goodideas.projectcube.ui.ReadArticle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.databinding.FragmentArticleDetailBinding
import com.goodideas.projectcube.ui.Login.userId
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ArticleDetail : Fragment() {
    lateinit var binding:FragmentArticleDetailBinding
    private val vm by viewModel<ArticleDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_article_detail, container, false)

        val articleId = arguments?.getInt("articleId") ?: Int.MIN_VALUE
        Timber.d(articleId.toString())
        if (articleId == Int.MIN_VALUE){
            Toast.makeText(this.requireContext(), "article mismatch, please try again",Toast.LENGTH_SHORT).show()
        }
        else vm.getSinglePost(articleId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObserver()

    }
    private fun initObserver(){
        vm.singlePostContent.observe(viewLifecycleOwner, Observer {
            binding.title.text = it.post?.get(0)?.title
            binding.content.text = it.post?.get(0)?.content
            val image = "http://api.rrrui.site/storage/" + it.post?.get(0)?.image
            Timber.d(it.toString())
            Glide.with(this.requireActivity())
                .load(image)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(binding.articleFirstImage)
        })

    }
    private fun initUI(){
        binding.more.setOnClickListener {
            val authorArticle = arrayOf("report","edit", "delete")
            val notAuthorArticle = arrayOf("report")

            MaterialAlertDialogBuilder(this.requireContext())
                .setItems(
                    if(vm.singlePostContent.value?.post?.get(0)?.id == userId) authorArticle
                    else notAuthorArticle
                ) { dialog, which ->
                    when(which){
                        0 -> Toast.makeText(this.requireContext(),"report $which", Toast.LENGTH_SHORT).show()
                        1 -> Toast.makeText(this.requireContext(),"edit $which", Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(this.requireContext(),"delete $which", Toast.LENGTH_SHORT).show()
                    }
                }
                .show()
        }
    }
}