package com.goodideas.projectcube.data.dto.posts
import com.google.gson.annotations.SerializedName

data class UpdatePostRes(
    @SerializedName("comments")
    val comments: List<UpdatePostComment>? = null,
    @SerializedName("post")
    val post: UpdatePost? = null
)

data class UpdatePostComment(
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
)

data class UpdatePost(
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
)