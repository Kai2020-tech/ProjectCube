package com.goodideas.projectcube.data.dto.auth
import com.google.gson.annotations.SerializedName


data class LoginRes(
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("user")
    val user: User? = null
)

data class User(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("dislike_score")
    val dislikeScore: Any? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("like_score")
    val likeScore: Any? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("post_count")
    val postCount: Any? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)