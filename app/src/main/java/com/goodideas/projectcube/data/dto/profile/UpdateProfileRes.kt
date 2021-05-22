package com.goodideas.projectcube.data.dto.profile

import com.google.gson.annotations.SerializedName


data class UpdateProfileRes(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)