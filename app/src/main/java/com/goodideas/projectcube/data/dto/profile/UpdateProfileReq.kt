package com.goodideas.projectcube.data.dto.profile

import okhttp3.MultipartBody

data class UpdateProfileReq(
    val name: MultipartBody.Part?,
    val avatar: MultipartBody.Part?
)