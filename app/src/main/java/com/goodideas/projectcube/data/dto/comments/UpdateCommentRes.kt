package com.goodideas.projectcube.data.dto.comments
import com.google.gson.annotations.SerializedName


data class UpdateCommentRes(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("parent_id")
    val parentId: Int? = null,
    @SerializedName("post_id")
    val postId: Int? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
)