package com.goodideas.projectcube.data.dto.posts
import com.google.gson.annotations.SerializedName


data class CreatePostRes(
    @SerializedName("first_command")
    val firstCommand: String? = null,
    @SerializedName("post")
    val post: Post? = null
)

data class Post(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
)