package com.goodideas.projectcube.ui.ReadArticle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.goodideas.projectcube.R
import com.goodideas.projectcube.databinding.FragmentArticleDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ArticleDetail : Fragment() {
    lateinit var binding:FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_article_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.more.setOnClickListener {
            //TODO check isauthor
            val authorArticle = arrayOf("edit", "delete", "report")
            val notAuthorArticle = arrayOf("report")

            MaterialAlertDialogBuilder(this.requireContext())
                .setItems(authorArticle) { dialog, which ->
                    when(authorArticle[which]){
                        "edit" -> Toast.makeText(this.requireContext(),"edit $which", Toast.LENGTH_SHORT).show()
                        "delete" -> Toast.makeText(this.requireContext(),"delete $which", Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(this.requireContext(),"report $which", Toast.LENGTH_SHORT).show()
                    }
                }
                .show()
        }
    }
}