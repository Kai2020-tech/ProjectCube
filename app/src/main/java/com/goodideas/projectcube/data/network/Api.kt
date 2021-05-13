package com.goodideas.projectcube.data.network

import com.goodideas.projectcube.data.dto.auth.LoginReq
import com.goodideas.projectcube.data.dto.auth.LoginRes
import com.goodideas.projectcube.data.dto.auth.LogoutRes
import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import com.goodideas.projectcube.data.dto.profile.ProfileRes
import com.goodideas.projectcube.data.dto.register.RegisterReq
import com.goodideas.projectcube.data.dto.register.RegisterRes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface Api {
    @POST("user/register")
    suspend fun register(@Body req: RegisterReq): Response<RegisterRes>

    @POST("user/login")
    suspend fun login(@Body req: LoginReq): Response<LoginRes>

    @POST("user/logout")
    suspend fun logout(): Response<LogoutRes>

    @POST("user/profile")
    suspend fun getProfile(): Response<ProfileRes>

    @GET("posts")
    suspend fun getPosts(): Response<Posts>

    @GET("posts/{postId}")
    suspend fun getSinglePost(
        @Path("postId")id: Int
    ): Response<SinglePostRes>
}