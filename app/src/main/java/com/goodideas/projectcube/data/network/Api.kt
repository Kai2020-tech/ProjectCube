package com.goodideas.projectcube.data.network

import com.goodideas.projectcube.data.dto.auth.LoginReq
import com.goodideas.projectcube.data.dto.auth.LoginRes
import com.goodideas.projectcube.data.dto.auth.LogoutRes
import com.goodideas.projectcube.data.dto.comments.*
import com.goodideas.projectcube.data.dto.posts.*
import com.goodideas.projectcube.data.dto.profile.ProfileRes
import com.goodideas.projectcube.data.dto.register.RegisterReq
import com.goodideas.projectcube.data.dto.register.RegisterRes
import com.goodideas.projectcube.data.dto.vote.VoteRes
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface Api {
    //user ------------------------------
    @POST("register")
    suspend fun register(@Body req: RegisterReq): Response<RegisterRes>

    @POST("login")
    suspend fun login(@Body req: LoginReq): Response<LoginRes>

    @POST("logout")
    suspend fun logout(): Response<LogoutRes>

    @POST("profile")
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
    @Multipart
    suspend fun createPost(
        @Part title: MultipartBody.Part?,
        @Part content: MultipartBody.Part?,
        @Part image: MultipartBody.Part?,
    ): Response<CreatePostRes>

    @PATCH("posts/{postId}")
    suspend fun updatePost(
        @Path("postId") id: Int,
        @Part title: MultipartBody.Part?,
        @Part content: MultipartBody.Part?,
        @Part image: MultipartBody.Part?,
    ): Response<UpdatePostRes>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path("postId") id: Int
    ): Response<DeletePostRes>


    //comments ------------------------------
    @GET("comments/{postId}/")
    suspend fun getCommentsOfPost(
        @Path("postId") id: Int
    ): Response<CommentsOfPostRes>

    @POST("comments/{postId}")
    suspend fun createComment(
        @Path("postId") id: Int,
        @Body req: CreateCommentReq
    ): Response<CreateCommentRes>

    @PATCH("comments/comment/{commentId}")
    suspend fun updateComment(
        @Path("commentId") id: Int,
        @Body req: CreateCommentReq
    ): Response<UpdateCommentRes>

    @DELETE("comments/comment/{commentId}")
    suspend fun deleteComment(
        @Path("commentId") id: Int
    ): Response<DeleteCommentRes>


    //vote ------------------------------
    @POST("votes/{postId}/{vote}")
    suspend fun votePost(
        @Path("postId") id: Int,
        @Path("vote") state:String
    ): Response<VoteRes>
}