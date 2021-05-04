package com.goodideas.projectcube.ui.ArticleList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.dto.posts.PostsItem

class ArticleAdapter:ListAdapter<PostsItem, ArticleAdapter.ArticleViewHolder>(DiffCompare()) {
    var click:()->Unit = {}
    
    class ArticleViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.article_title)
        val content = itemView.findViewById<TextView>(R.id.article_content_outline)
        val date = itemView.findViewById<TextView>(R.id.article_post_time)
        val like = itemView.findViewById<TextView>(R.id.like_number)
        val dislike = itemView.findViewById<TextView>(R.id.dislike_number)
    }

    class DiffCompare: ItemCallback<PostsItem>(){
        override fun areItemsTheSame(oldItem: PostsItem, newItem:PostsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostsItem, newItem: PostsItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_viewholder,parent,false)
        return ArticleViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val (content,created,_,title,_ ) = getItem(position)
        holder.let {
            it.content.text = content
            it.date.text = created
            it.title.text = title
            it.itemView.setOnClickListener { click() }
            it.like.text = "50"
            it.dislike.text = "20"
        }
    }
}