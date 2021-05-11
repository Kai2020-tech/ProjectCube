package com.goodideas.projectcube.data.network

import com.goodideas.projectcube.data.dto.login.LoginReq
import com.goodideas.projectcube.data.dto.login.LoginRes
import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.dto.register.RegisterReq
import com.goodideas.projectcube.data.dto.register.RegisterRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface Api {
    @POST("user/register")
    @Headers("Accept: application/json")
    suspend fun register(@Body req: RegisterReq): Response<RegisterRes>

    @POST("user/login")
    @Headers("Accept: application/json")
    suspend fun login(@Body req: LoginReq): Response<LoginRes>

    @GET("posts")
    suspend fun getPosts(): Response<Posts>
}