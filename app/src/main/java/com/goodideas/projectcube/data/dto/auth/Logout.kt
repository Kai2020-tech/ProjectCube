package com.goodideas.projectcube.data.dto.auth
import com.google.gson.annotations.SerializedName


data class LogoutRes(
    @SerializedName("message")
    val message: String? = null
)
