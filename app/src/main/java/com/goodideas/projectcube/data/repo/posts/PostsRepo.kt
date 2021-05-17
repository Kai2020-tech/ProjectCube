package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import com.goodideas.projectcube.data.network.ApiService
import retrofit2.Response

class PostsRepo(private val source: ApiService) : IPostsRepo {
    override suspend fun getAllPosts(): Response<AllPosts> {
        return source.retrofit.getAllPosts()
    }

    override suspend fun getSinglePost(id: Int): Response<SinglePostRes> {
        return source.retrofit.getSinglePost(id)
    }
}