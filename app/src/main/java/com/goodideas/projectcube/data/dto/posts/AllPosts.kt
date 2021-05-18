package com.goodideas.projectcube.data.dto.posts

import com.google.gson.annotations.SerializedName


//rui 5/17
class AllPosts : ArrayList<AllPostsItem>()
data class AllPostsItem(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dislike_count")
    val dislikeCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)