package com.goodideas.projectcube.ui.articleList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.posts.AllPostsItem

class ArticleAdapter:ListAdapter<AllPostsItem, ArticleAdapter.ArticleViewHolder>(DiffCompare()) {
    var click:(Int)->Unit = {}
    
    class ArticleViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val titleView = itemView.findViewById<TextView>(R.id.article_title)
        val contentView = itemView.findViewById<TextView>(R.id.article_content_outline)
        val date = itemView.findViewById<TextView>(R.id.article_post_time)
        val like = itemView.findViewById<TextView>(R.id.like_number)
        val dislike = itemView.findViewById<TextView>(R.id.dislike_number)
    }

    class DiffCompare: ItemCallback<AllPostsItem>(){
        override fun areItemsTheSame(oldItem: AllPostsItem, newItem:AllPostsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AllPostsItem, newItem: AllPostsItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_viewholder,parent,false)
        return ArticleViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val (_,commentCount,content,created,dislike,articleId,image,like,title,update,authorId) = getItem(position)
        holder.let {
            it.contentView.text = content
            it.date.text = created
            it.titleView.text = title
            it.itemView.setOnClickListener { click(articleId) }
            it.like.text = like.toString()
            it.dislike.text = dislike.toString()
        }
    }
}