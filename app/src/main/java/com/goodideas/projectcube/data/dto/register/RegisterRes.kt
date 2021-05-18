package com.goodideas.projectcube.data.dto.register

import com.google.gson.annotations.SerializedName


data class RegisterRes(
    @SerializedName("token")
    val token: String = "", // personal_access_token
    @SerializedName("user")
    val user: User = User()
) {
    data class User(
        @SerializedName("avatar")
        val avatar: String = "", // null
        @SerializedName("created_at")
        val createdAt: String = "", // 2021-05-14T11:07:14.000000Z
        @SerializedName("email")
        val email: String = "", // regi@regi.com
        @SerializedName("id")
        val id: Int = 0, // 6
        @SerializedName("name")
        val name: String = "", // regi
        @SerializedName("updated_at")
        val updatedAt: String = "" // 2021-05-14T11:07:14.000000Z
    )
}