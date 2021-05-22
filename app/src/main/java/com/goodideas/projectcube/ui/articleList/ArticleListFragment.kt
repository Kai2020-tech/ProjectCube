package com.goodideas.projectcube.ui.articleList

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.Util.hideKeyboard
import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.dto.posts.AllPostsItem
import com.goodideas.projectcube.data.dto.vote.VoteState
import com.goodideas.projectcube.data.dto.vote.VoteType
import com.goodideas.projectcube.data.network.token
import com.goodideas.projectcube.databinding.FragmentArticleListBinding
import com.goodideas.projectcube.ui.Login.StartViewModel
import com.goodideas.projectcube.ui.Login.userId
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ArticleListFragment : Fragment() {
    lateinit var binding: FragmentArticleListBinding
    lateinit var recyclerView: RecyclerView
    private var email:String? = null
    private var password:String? = null
    lateinit var adapter: ArticleAdapter

    private val vm by viewModel<ArticleListViewModel>()
    private val vmLogin by viewModel<StartViewModel>()

    companion object{
        private val LOGIN_EMAIL = "emailLogin"
        private val LOGIN_PASSWORD = "passwordLogin"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_article_list, container, false
        )
        autoLogin()
        vm.getPosts()
        adapter = ArticleAdapter(this.requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initUI()
    }

    private fun autoLogin(){

        email = this.requireActivity().getPreferences(Context.MODE_PRIVATE)
            .getString(LOGIN_EMAIL,null)
        password = this.requireActivity().getPreferences(Context.MODE_PRIVATE)
            .getString(LOGIN_PASSWORD,null)

        Timber.d(email)
        Timber.d(password)
        if (email == null && password == null){
            email = arguments?.getString("em")
            password = arguments?.getString("ps")

            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString(LOGIN_EMAIL, email)
                putString(LOGIN_PASSWORD,password)
                commit()
            }
        }
        if (token != "") return

        if (token == "" && email != null && password != null){
            vmLogin.login(email!!,password!!)
        } else {
            findNavController().navigate(R.id.action_articleListFragment_to_startFragment)
        }

        vmLogin.loginResult.observe(viewLifecycleOwner, Observer {
            if (it == ResponseStatus.FAIL) findNavController().navigate(R.id.action_articleListFragment_to_startFragment)
        })
    }

    private fun initObserver(){
        vm.allPostsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it ?: AllPosts())
        })
    }

    private fun initUI(){
        binding.articleSearch.paint.flags = Paint.UNDERLINE_TEXT_FLAG

        if (userId != Int.MIN_VALUE && token != "") binding.createNewArticle.visibility = View.VISIBLE
        binding.createNewArticle.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_articleListFragment_to_createPostFragment)
        }
        initAdapter()

        binding.articleSearch.setOnClickListener {
            val dialogView = this.requireActivity().layoutInflater.inflate(R.layout.comment_layout,null)
            val query = dialogView.findViewById<EditText>(R.id.comment_write_dialog_edittext)
            AlertDialog.Builder(this.requireContext())
                .setTitle("search for ...")
                .setPositiveButton("search"){_,which ->
                    view?.findNavController()?.navigate(R.id.action_articleListFragment_to_searchFragment,
                    bundleOf("keyword" to query.text.toString()))
                    hideKeyboard(it)
                }
                .setView(dialogView)
                .show()
        }
    }
    private fun initAdapter(){
        recyclerView = binding.articleRecyclerList
        recyclerView.addItemDecoration(DividerItemDecoration(this.requireContext(),DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.click = {
            Timber.d(it.toString())
            view?.findNavController()?.navigate(R.id.action_articleListFragment_to_articleDetail,
                bundleOf("articleId" to it))
        }
        adapter.like = {
            vm.vote(VoteType.POST,it,VoteState.LIKE)
        }
        adapter.disLike = {
            vm.vote(VoteType.POST,it,VoteState.DISLIKE)
        }
    }
}