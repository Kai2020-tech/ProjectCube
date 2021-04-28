package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.network.ApiService
import retrofit2.Response

class PostsRepo(private val source: ApiService) : IPostsRepo {
    override suspend fun getPosts(): Response<Posts> {
        return source.retrofit.getPosts()
    }
}