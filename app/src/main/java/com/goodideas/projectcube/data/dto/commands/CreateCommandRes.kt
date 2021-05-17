package com.goodideas.projectcube.data.dto.commands
import com.google.gson.annotations.SerializedName


data class CreateCommandRes(
    @SerializedName("content")
    val content: String = "", // 20210517
    @SerializedName("created_at")
    val createdAt: String = "", // 2021-05-17T03:29:24.000000Z
    @SerializedName("id")
    val id: Int = 0, // 227
    @SerializedName("post_id")
    val postId: Int = 0, // 3
    @SerializedName("updated_at")
    val updatedAt: String = "", // 2021-05-17T03:29:24.000000Z
    @SerializedName("user_id")
    val userId: Int = 0 // 22
)

