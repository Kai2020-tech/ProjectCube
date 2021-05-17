package com.goodideas.projectcube.data.dto.commands

data class CreateCommandReq(
    val postId: Int = 0,
    val content: String = ""
)