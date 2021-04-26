package com.goodideas.projectcube.data.network

import com.goodideas.projectcube.data.dto.register.RegisterReq
import com.goodideas.projectcube.data.dto.register.RegisterRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface Api {

    @POST("register")
    suspend fun register(@Body req: RegisterReq): Response<RegisterRes>
}