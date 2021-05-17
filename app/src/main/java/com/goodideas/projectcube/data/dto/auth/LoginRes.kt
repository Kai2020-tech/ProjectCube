package com.goodideas.projectcube.data.dto.auth
import com.google.gson.annotations.SerializedName

data class LoginRes(
    @SerializedName("token")
    val token: String = "", // personal_access_token
    @SerializedName("user")
    val user: User = User()
) {
    data class User(
        @SerializedName("avatar")
        val avatar: String = "", // avatar/fag5sOsdas5yWG5TQ04SlwnMZJSSdsdssk4wUek.jpg
        @SerializedName("created_at")
        val createdAt: String = "", // 2021-05-14T11:07:14.000000Z
        @SerializedName("email")
        val email: String = "", // regi@regi.com
        @SerializedName("email_verified_at")
        val emailVerifiedAt: Any = Any(), // null
        @SerializedName("id")
        val id: Int = 0, // 6
        @SerializedName("name")
        val name: String = "", // regi
        @SerializedName("updated_at")
        val updatedAt: String = "" // 2021-05-14T11:07:14.000000Z
    )
}