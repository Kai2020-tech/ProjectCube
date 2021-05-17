package com.goodideas.projectcube.data.dto.posts
import com.google.gson.annotations.SerializedName

data class CreatePostRes(
    @SerializedName("content")
    val content: String = "", // testContent
    @SerializedName("created_at")
    val createdAt: String = "", // 2021-05-14T16:58:04.000000Z
    @SerializedName("id")
    val id: Int = 0, // 1
    @SerializedName("image")
    val image: String = "", // null
    @SerializedName("title")
    val title: String = "", // testTitle
    @SerializedName("updated_at")
    val updatedAt: String = "", // 2021-05-14T16:58:04.000000Z
    @SerializedName("user_id")
    val userId: Int = 0 // 6
)