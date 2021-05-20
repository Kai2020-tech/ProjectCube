package com.goodideas.projectcube.ui.SearchPost

import android.os.Bundle
import android.os.RecoverySystem
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.hideKeyboard
import com.goodideas.projectcube.databinding.FragmentSearchBinding
import com.goodideas.projectcube.ui.articleList.ArticleAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class SearchFragment : Fragment() {
    lateinit var binding:FragmentSearchBinding
    lateinit var sadapter:ArticleAdapter
    lateinit var rv:RecyclerView
    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObserver()
    }
    private fun initObserver(){
        viewModel.searchResult.observe(viewLifecycleOwner, Observer {
            if(it.size == 0 ) Toast.makeText(this.requireContext(), "no result found", Toast.LENGTH_SHORT).show()
            sadapter.submitList(it)
        })
    }
    private fun initUi(){
        val searchKeyWord = arguments?.getString("keyword")
        if (searchKeyWord.isNullOrBlank()) Toast.makeText(this.requireContext(),"please type word",Toast.LENGTH_SHORT).show()
        else viewModel.searchPost(searchKeyWord)
//        arguments?.putString("keyword",null)

        sadapter = ArticleAdapter((this.requireContext()))
        rv = binding.searchRecyclerList
        rv.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.requireContext())
            adapter = sadapter
            addItemDecoration(
                DividerItemDecoration(this@SearchFragment.requireContext(),
                    DividerItemDecoration.VERTICAL)
            )
        }

        binding.articleSearchS.setOnClickListener {
            val dialogView = this.requireActivity().layoutInflater.inflate(R.layout.comment_layout,null)
            val query = dialogView.findViewById<EditText>(R.id.comment_write_dialog_edittext)
            AlertDialog.Builder(this.requireContext())
                .setTitle("search for ...")
                .setPositiveButton("search"){_,which ->
                    viewModel.searchPost(query.text.toString())
                    hideKeyboard(it)
                }
                .setView(dialogView)
                .show()
        }
    }
}