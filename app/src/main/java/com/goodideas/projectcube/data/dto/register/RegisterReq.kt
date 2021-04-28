package com.goodideas.projectcube.data.dto.register

data class RegisterReq(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)