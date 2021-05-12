package com.goodideas.projectcube.data.dto.profile
import com.google.gson.annotations.SerializedName


data class ProfileRes(
    @SerializedName("created_at")
    val createdAt: String = "", // 2021-05-11T00:35:32.000000Z
    @SerializedName("dislike_score")
    val dislikeScore: Any = Any(), // null
    @SerializedName("email")
    val email: String = "", // N19@gamil.com
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any = Any(), // null
    @SerializedName("id")
    val id: Int = 0, // 11
    @SerializedName("like_score")
    val likeScore: Any = Any(), // null
    @SerializedName("name")
    val name: String = "", // N19
    @SerializedName("post_count")
    val postCount: Any = Any(), // null
    @SerializedName("updated_at")
    val updatedAt: String = "" // 2021-05-11T00:35:32.000000Z
)