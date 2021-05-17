package com.goodideas.projectcube.data.dto.posts
import com.google.gson.annotations.SerializedName


data class SinglePostRes(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("dislikes")
    val dislikes: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("likes")
    val likes: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
)