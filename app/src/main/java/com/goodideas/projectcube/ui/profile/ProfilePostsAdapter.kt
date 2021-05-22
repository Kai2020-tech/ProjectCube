package com.goodideas.projectcube.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.profile.Data
import com.goodideas.projectcube.ui.ReadArticle.imagePrefix
import org.w3c.dom.Text

class ProfilePostsAdapter(val context: Context):ListAdapter<Data,ProfilePostsAdapter.PpViewHolder>(Dif()) {

    class PpViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById<TextView>(R.id.article_title)
        val contentView = itemView.findViewById<TextView>(R.id.article_content_outline)
        val like = itemView.findViewById<TextView>(R.id.like_number)
        val dislike = itemView.findViewById<TextView>(R.id.dislike_number)
        val previewImage: ImageView = itemView.findViewById(R.id.preview_image)
        val commentCount:TextView = itemView.findViewById(R.id.comment_count)
    }

    class Dif:DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PpViewHolder {
        val t = LayoutInflater.from(parent.context).inflate(
                R.layout.article_viewholder,parent,false)
        return PpViewHolder(t)
    }

    override fun onBindViewHolder(holder: PpViewHolder, position: Int) {
        val data = getItem(position)
        holder.apply {
            titleView.text = data.title
            contentView.text = data.content
            Glide.with(context)
                .load(imagePrefix + data.image)
                .into(previewImage)
            like.visibility = View.GONE
            dislike.visibility = View.GONE
            contentView.visibility = View.GONE
        }
    }
}