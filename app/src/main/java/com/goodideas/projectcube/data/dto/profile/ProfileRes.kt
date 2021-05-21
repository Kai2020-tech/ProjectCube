package com.goodideas.projectcube.data.dto.profile
import com.google.gson.annotations.SerializedName


data class ProfileRes(
    @SerializedName("posts")
    val posts: Posts? = null,
    @SerializedName("user")
    val user: User? = null
)

data class Posts(
    @SerializedName("current_page")
    val currentPage: Int? = null,
    @SerializedName("data")
    val `data`: List<Data>? = null,
    @SerializedName("first_page_url")
    val firstPageUrl: String? = null,
    @SerializedName("from")
    val from: Int? = null,
    @SerializedName("last_page")
    val lastPage: Int? = null,
    @SerializedName("last_page_url")
    val lastPageUrl: String? = null,
    @SerializedName("links")
    val links: List<Link>? = null,
    @SerializedName("next_page_url")
    val nextPageUrl: String? = null,
    @SerializedName("path")
    val path: String? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    @SerializedName("prev_page_url")
    val prevPageUrl: String? = null,
    @SerializedName("to")
    val to: Int? = null,
    @SerializedName("total")
    val total: Int? = null
)

data class User(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("comments_count")
    val commentsCount: Int? = null,
    @SerializedName("dislike_count")
    val dislikeCount: Int? = null,
    @SerializedName("like_count")
    val likeCount: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("post_count")
    val postCount: Int? = null
)

data class Data(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
)

data class Link(
    @SerializedName("active")
    val active: Boolean? = null,
    @SerializedName("label")
    val label: String? = null,
    @SerializedName("url")
    val url: Any? = null
)