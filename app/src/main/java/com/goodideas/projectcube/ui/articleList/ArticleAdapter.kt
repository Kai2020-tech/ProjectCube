package com.goodideas.projectcube.ui.articleList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.data.dto.posts.AllPostsItem
import com.goodideas.projectcube.ui.customView.LikeDislikeCompare
import timber.log.Timber

class ArticleAdapter(val context: Context):ListAdapter<AllPostsItem, ArticleAdapter.ArticleViewHolder>(DiffCompare()) {
    var click:(Int)->Unit = {}
    var like:(Int)->Unit = {}
    var disLike:(Int) -> Unit = {}
    
    class ArticleViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById<TextView>(R.id.article_title)
        val contentView = itemView.findViewById<TextView>(R.id.article_content_outline)
//        val date = itemView.findViewById<TextView>(R.id.article_post_time)
        val like = itemView.findViewById<TextView>(R.id.like_number)
        val dislike = itemView.findViewById<TextView>(R.id.dislike_number)
        val previewImage: ImageView = itemView.findViewById(R.id.preview_image)
        val commentCount:TextView = itemView.findViewById(R.id.comment_count)

        val custom:LikeDislikeCompare = itemView.findViewById(R.id.count)
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
        val (avatar,commentCount,content,created,dislike,articleId,image,like,name,title,update,authorId) = getItem(position)
        holder.let {
            it.contentView.text = content
//            it.date.text = created
            it.titleView.text = title
            it.itemView.setOnClickListener { click(articleId) }
            it.like.text = like.toString()
            it.like.setOnClickListener { like(articleId) }
            it.dislike.text = dislike.toString()
            it.dislike.setOnClickListener { disLike(articleId) }
            it.commentCount.text = commentCount.toString()
            //中:如果沒圖片位址，不繪製
            if (image.isNotBlank() && image != "null"){
                it.previewImage.setWillNotDraw(false)
                Glide.with(context)
                    .load("http://api.rrrui.site/storage/$image")
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(it.previewImage)
            } else {
                it.previewImage.setWillNotDraw(true)
            }
            //中:把like和dislike的值傳給customView繪製
            it.custom.setLikeAndDislike(like,dislike)
        }
    }
}