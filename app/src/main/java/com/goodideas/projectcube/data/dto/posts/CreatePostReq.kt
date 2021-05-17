package com.goodideas.projectcube.data.dto.posts

import com.google.gson.annotations.SerializedName

data class CreatePostReq(
    val title: String = "",
    val content: String = "",
    @SerializedName("image")
    val image: Any = Any() // null
)