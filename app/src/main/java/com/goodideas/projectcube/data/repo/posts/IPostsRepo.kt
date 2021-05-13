package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import retrofit2.Response

interface IPostsRepo {
    suspend fun getPosts(): Response<Posts>
    suspend fun getSinglePost(id: Int): Response<SinglePostRes>
}