package com.goodideas.projectcube.ui.ReadArticle

import android.content.Context
import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.posts.Comment
import com.goodideas.projectcube.ui.articleList.ArticleAdapter

class CommentAdapter(val context:Context):ListAdapter<Comment,CommentAdapter.CommentViewHolder>(Diff()) {
    var longClick:(Int?,String?,Int?) -> Unit = {_,_,_ ->}
    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val comment:TextView = itemView.findViewById(R.id.comment_content)
        val nameView:TextView = itemView.findViewById(R.id.comment_name)
        val avatar:ImageView = itemView.findViewById(R.id.comment_avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val tmp = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_viewholder, parent, false)
        return  CommentViewHolder(tmp)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val (avatarStr,content,_,dislike,commentId,like,name,articleId,postId,_,commentAuthorId) = getItem(position)
        holder.apply {
            comment.text = content
            nameView.text = name
            if (avatarStr != null){
                avatar.setWillNotDraw(false)
                Glide.with(context)
                    .load(imagePrefix + avatarStr)
                    .into(avatar)
            } else {
                avatar.setWillNotDraw(true)
            }
            itemView.setOnLongClickListener {
                longClick(commentId,content,commentAuthorId)
                return@setOnLongClickListener true
            }
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