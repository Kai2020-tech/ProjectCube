package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.posts.Posts
import retrofit2.Response

interface IPostsRepo {
    suspend fun getPosts():Response<Posts>
}