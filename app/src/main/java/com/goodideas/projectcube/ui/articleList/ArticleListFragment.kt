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
import com.goodideas.projectcube.databinding.FragmentArticleListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


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
            view?.findNavController()?.navigate(R.id.action_articleListFragment_to_articleDetail,
                bundleOf(Pair("articleId", it)))
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
//            adapter.submitList(it ?: listOf(
//                AllPosts.PostsItem(
//                    "empty content", "date",
//                    Int.MIN_VALUE, "empty title", "update"
//                )
//            ))
        })
    }

    private fun initUI(){
        binding.createNewArticle.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_articleListFragment_to_createPostFragment)
        }
    }
}