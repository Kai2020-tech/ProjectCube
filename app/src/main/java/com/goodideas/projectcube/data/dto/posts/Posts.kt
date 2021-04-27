package com.goodideas.projectcube.data.dto.posts

class Posts : ArrayList<PostsItem>()

data class PostsItem(
    val content: String,
    val created_at: String,
    val id: Int,
    val title: String,
    val updated_at: String
)