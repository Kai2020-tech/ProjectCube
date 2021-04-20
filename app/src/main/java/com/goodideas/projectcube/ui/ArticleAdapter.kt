package com.goodideas.projectcube.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter:ListAdapter<List<Int>,ArticleAdapter.ArticleViewHolder>(diffCompare()) {

    class ArticleViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    class diffCompare: ItemCallback<List<Int>>(){
        override fun areItemsTheSame(oldItem: List<Int>, newItem: List<Int>): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: List<Int>, newItem: List<Int>): Boolean {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}