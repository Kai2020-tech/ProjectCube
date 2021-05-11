package com.goodideas.projectcube.data.dto.auth
import com.google.gson.annotations.SerializedName


data class LogoutReq(
    val token: String=""
)

data class LogoutRes(
    @SerializedName("message")
    val message: String? = null
)
