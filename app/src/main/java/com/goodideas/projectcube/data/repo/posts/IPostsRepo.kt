package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.posts.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Part

interface IPostsRepo {
    suspend fun getAllPosts(): Response<AllPosts>

    suspend fun getSinglePost(id: Int): Response<SinglePostRes>

    suspend fun createPost(
        title: MultipartBody.Part?,
        content: MultipartBody.Part?,
        image: MultipartBody.Part?
    ): Response<CreatePostRes>

    suspend fun updatePost(
        id: Int,
        title: MultipartBody.Part?,
        content: MultipartBody.Part?,
        image: MultipartBody.Part?
    ): Response<UpdatePostRes>

    suspend fun deletePost(id: Int): Response<DeletePostRes>

    suspend fun searchPost(keyword: String): Response<SearchPostRes>
}