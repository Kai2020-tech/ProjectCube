package com.goodideas.projectcube.ui.articleList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.network.token
import com.goodideas.projectcube.databinding.FragmentArticleListBinding
import com.goodideas.projectcube.ui.Login.userId
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ArticleListFragment : Fragment() {
    lateinit var binding: FragmentArticleListBinding
    lateinit var recyclerView: RecyclerView
    val adapter = ArticleAdapter()

    private val vm by viewModel<ArticleListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_article_list, container, false
        )

        recyclerView = binding.articleRecyclerList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.click = {
            Timber.d(it.toString())
            view?.findNavController()?.navigate(R.id.action_articleListFragment_to_articleDetail,
                bundleOf("articleId" to it))
        }

        vm.getPosts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initUI()
    }
    private fun initObserver(){
        vm.allPostsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it ?: AllPosts())
        })
    }

    private fun initUI(){
        if (userId != Int.MIN_VALUE && token != "") binding.createNewArticle.visibility = View.VISIBLE
        binding.createNewArticle.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_articleListFragment_to_createPostFragment)
        }
    }
}