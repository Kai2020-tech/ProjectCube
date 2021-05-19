package com.goodideas.projectcube.data.dto.comments
import com.google.gson.annotations.SerializedName


class CommentsOfPostRes : ArrayList<CommentsOfPostRes.CommentsItem>(){
    data class CommentsItem(
        @SerializedName("content")
        val content: String = "", // 葛文
        @SerializedName("created_at")
        val createdAt: String = "", // 2021-05-13T01:58:04.000000Z
        @SerializedName("dislikes")
        val dislikes: Int = 0, // 2
        @SerializedName("id")
        val id: Int = 0, // 203
        @SerializedName("likes")
        val likes: Int = 0, // 10
        @SerializedName("post_id")
        val postId: Int = 0, // 1
        @SerializedName("updated_at")
        val updatedAt: String = "", // 2021-05-13T01:58:04.000000Z
        @SerializedName("user_id")
        val userId: Int = 0 // 1
    )
}