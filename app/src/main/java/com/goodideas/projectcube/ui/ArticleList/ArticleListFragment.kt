package com.goodideas.projectcube.ui.ArticleList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.dto.posts.PostsItem
import com.goodideas.projectcube.databinding.FragmentArticleListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            //need pass article id
            view?.findNavController()?.navigate(R.id.action_articleListFragment_to_articleDetail)
        }

        vm.getPosts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.postsList.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it ?: listOf(
//                PostsItem("content","date",
//                    Int.MIN_VALUE,"title","update")))
            //fake data
            adapter.submitList(listOf(
                PostsItem("content","date",
                    Int.MIN_VALUE,"title","update"),
                PostsItem("content2","date",
                    Int.MIN_VALUE,"title2","update")))
        })



    }
}