package com.goodideas.projectcube.data.dto.posts
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//single post , 為什麼用list<post>

//@Parcelize
//data class SinglePostRes(
//    @SerializedName("comments")
//    val comments: List<Comment>? = null,
//    @SerializedName("post")
//    val post: List<Post>? = null
//) : Parcelable
//
//@Parcelize
//data class Comment(
//    @SerializedName("content")
//    val content: String? = null,
//    @SerializedName("created_at")
//    val createdAt: String? = null,
//    @SerializedName("id")
//    val id: Int? = null,
//    @SerializedName("parent_id")
//    val parentId: Int? = null,
//    @SerializedName("post_id")
//    val postId: Int? = null,
//    @SerializedName("updated_at")
//    val updatedAt: String? = null,
//    @SerializedName("user_id")
//    val userId: Int? = null
//) : Parcelable
//
//@Parcelize
//data class Post(
//    @SerializedName("avatar")
//    val avatar: String? = null,
//    @SerializedName("comment_count")
//    val commentCount: Int? = null,
//    @SerializedName("content")
//    val content: String? = null,
//    @SerializedName("created_at")
//    val createdAt: String? = null,
//    @SerializedName("dislike_count")
//    val dislikeCount: Int? = null,
//    @SerializedName("id")
//    val id: Int? = null,
//    @SerializedName("image")
//    val image: String? = null,
//    @SerializedName("like_count")
//    val likeCount: Int? = null,
//    @SerializedName("name")
//    val name: String? = null,
//    @SerializedName("title")
//    val title: String? = null,
//    @SerializedName("updated_at")
//    val updatedAt: String? = null,
//    @SerializedName("user_id")
//    val userId: Int? = null
//) : Parcelable

@Parcelize
data class SinglePostRes(
    @SerializedName("comments")
    val comments: List<Comment>? = null,
    @SerializedName("post")
    val post: Post? = null
): Parcelable

@Parcelize
data class Comment(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("dislike_count")
    val dislikeCount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("like_count")
    val likeCount: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("parent_id")
    val parentId: Int? = null,
    @SerializedName("post_id")
    val postId: Int? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
): Parcelable

@Parcelize
data class Post(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("comment_count")
    val commentCount: Int? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("dislike_count")
    val dislikeCount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("like_count")
    val likeCount: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
): Parcelable