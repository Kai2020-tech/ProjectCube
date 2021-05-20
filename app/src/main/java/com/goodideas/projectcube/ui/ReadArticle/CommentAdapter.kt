package com.goodideas.projectcube.ui.ReadArticle

import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.posts.Comment
import com.goodideas.projectcube.ui.articleList.ArticleAdapter

class CommentAdapter:ListAdapter<Comment,CommentAdapter.CommentViewHolder>(Diff()) {
    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val comment:TextView = itemView.findViewById(R.id.comment_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val tmp = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_viewholder, parent, false)
        return  CommentViewHolder(tmp)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val (content,_,commentId,parentCommentId,articleId,_,commentAuthorId) = getItem(position)
        holder.apply {
            comment.text = content
        }
    }

    class Diff: DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Comment, newItem:Comment): Boolean {
            return oldItem == newItem
        }
    }
}