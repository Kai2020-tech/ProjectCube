package com.goodideas.projectcube.data.dto.vote
import com.google.gson.annotations.SerializedName


data class VoteRes(
    @SerializedName("dislike_count")
    val dislikeCount: Int? = null,
    @SerializedName("like_count")
    val likeCount: Int? = null,
    @SerializedName("voted")
    val voted: Voted? = null
)

data class Voted(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("post_id")
    val postId: String? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
)