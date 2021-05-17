package com.goodideas.projectcube.data.network

import com.goodideas.projectcube.data.dto.auth.LoginReq
import com.goodideas.projectcube.data.dto.auth.LoginRes
import com.goodideas.projectcube.data.dto.auth.LogoutRes
import com.goodideas.projectcube.data.dto.commands.CommandsOfPostRes
import com.goodideas.projectcube.data.dto.commands.CreateCommandReq
import com.goodideas.projectcube.data.dto.commands.CreateCommandRes
import com.goodideas.projectcube.data.dto.posts.*
import com.goodideas.projectcube.data.dto.profile.ProfileRes
import com.goodideas.projectcube.data.dto.register.RegisterReq
import com.goodideas.projectcube.data.dto.register.RegisterRes
import retrofit2.Response
import retrofit2.http.*


interface Api {
    //user ------------------------------
    @POST("user/register")
    suspend fun register(@Body req: RegisterReq): Response<RegisterRes>

    @POST("user/login")
    suspend fun login(@Body req: LoginReq): Response<LoginRes>

    @POST("user/logout")
    suspend fun logout(): Response<LogoutRes>

    @POST("user/profile")
    suspend fun getProfile(): Response<ProfileRes>

    //posts ------------------------------
    @GET("posts")
    suspend fun getAllPosts(): Response<AllPosts>

    @GET("posts/{postId}")
    suspend fun getSinglePost(
        @Path("postId") id: Int
    ): Response<SinglePostRes>

    @GET("posts/search/{keyword}")
    suspend fun getSearchedPost(
        @Path("keyword") keyword: String
    ): Response<SearchPostRes>

    @POST("posts")
    suspend fun createPost(@Body req: CreatePostReq): Response<CreatePostRes>

    @PUT("posts/{postId}")
    suspend fun updatePost(
        @Path("postId") id: Int,
        @Body req: CreatePostReq
    ): Response<SinglePostRes>

    //commands ------------------------------
    @GET("posts/{postId}/commands")
    suspend fun getCommandsOfPost(
        @Path("postId") id: Int
    ): Response<CommandsOfPostRes>

    @POST("commands")
    suspend fun createCommand(
        @Body req: CreateCommandReq
    ): Response<CreateCommandRes>
}