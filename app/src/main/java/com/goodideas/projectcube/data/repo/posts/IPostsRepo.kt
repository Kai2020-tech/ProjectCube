package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import retrofit2.Response

interface IPostsRepo {
    suspend fun getAllPosts(): Response<AllPosts>
    suspend fun getSinglePost(id: Int): Response<SinglePostRes>
}