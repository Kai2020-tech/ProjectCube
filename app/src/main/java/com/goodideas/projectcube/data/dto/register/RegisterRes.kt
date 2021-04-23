package com.goodideas.projectcube.data.dto.register

import com.google.gson.annotations.SerializedName


data class RegisterRes(
    @SerializedName("token")
    val token: String,

    @SerializedName("user")
    val user: User
) {
    data class User(

        @SerializedName("created_at")
        val created_at: String,

        @SerializedName("email")
        val email: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("updated_at")
        val updated_at: String
    )
}

