package com.goodideas.projectcube.data.dto.posts
import com.google.gson.annotations.SerializedName


data class DeletePostRes(
    @SerializedName("message")
    val message: String? = null
)