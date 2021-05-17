package com.goodideas.projectcube.data.dto.posts
import com.google.gson.annotations.SerializedName




class AllPosts : ArrayList<AllPosts.PostsItem>(){
    data class PostsItem(
        @SerializedName("avatar")
        val avatar: String = "", // null
        @SerializedName("comment_count")
        val commentCount: Int = 0, // 1
        @SerializedName("content")
        val content: String = "", // testContent2
        @SerializedName("created_at")
        val createdAt: String = "", // 2021-05-04 06:40:18
        @SerializedName("dislike_count")
        val dislikeCount: Int = 0, // 1
        @SerializedName("id")
        val id: Int = 0, // 2
        @SerializedName("image")
        val image: String = "", // null
        @SerializedName("like_count")
        val likeCount: Int = 0, // 2
        @SerializedName("name")
        val name: String = "", // user2
        @SerializedName("title")
        val title: String = "", // testTitle2
        @SerializedName("updated_at")
        val updatedAt: String = "", // 2021-05-04 06:51:42
        @SerializedName("user_id")
        val userId: Int = 0 // 2
    )
}