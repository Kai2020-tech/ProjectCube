package com.goodideas.projectcube.data.dto.comments

import com.google.gson.annotations.SerializedName

data class DeleteCommentRes(
    @SerializedName("message")
    val message: String? = null
)