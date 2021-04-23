package com.goodideas.projectcube.data.dto.register

data class ErrorResponse(
    val detail: String?,
    val status: Int,
    val title: String,
    val type: String
)