package com.goodideas.projectcube.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.goodideas.projectcube.R
import com.goodideas.projectcube.databinding.FragmentArticleListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleListFragment : Fragment() {
    lateinit var binding: FragmentArticleListBinding

     private val vm by viewModel<ArticleListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_article_list, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getPosts()
    }
}