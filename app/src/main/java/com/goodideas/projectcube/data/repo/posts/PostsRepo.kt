package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.posts.*
import com.goodideas.projectcube.data.dto.vote.VoteRes
import com.goodideas.projectcube.data.network.ApiService
import okhttp3.MultipartBody
import retrofit2.Response

class PostsRepo(private val source: ApiService) : IPostsRepo {
    override suspend fun getAllPosts(): Response<AllPosts> {
        return source.retrofit.getAllPosts()
    }

    override suspend fun getSinglePost(id: Int): Response<SinglePostRes> {
        return source.retrofit.getSinglePost(id)
    }

    override suspend fun createPost(
        title: MultipartBody.Part?,
        content: MultipartBody.Part?,
        image: MultipartBody.Part?
    ): Response<CreatePostRes> {
        return source.retrofit.createPost(title, content, image)
    }

    override suspend fun updatePost(
        id: Int,
        title: MultipartBody.Part?,
        content: MultipartBody.Part?,
        image: MultipartBody.Part?
    ): Response<UpdatePostRes> {
        return source.retrofit.updatePost(id, title, content, image)
    }

    override suspend fun deletePost(id: Int): Response<DeletePostRes> {
        return source.retrofit.deletePost(id)
    }

    override suspend fun searchPost(keyword: String): Response<SearchPostRes> {
        return source.retrofit.getSearchedPost(keyword)
    }

    override suspend fun votePost(id: Int, state: String): Response<VoteRes> {
        return source.retrofit.votePost(id, state)
    }
}