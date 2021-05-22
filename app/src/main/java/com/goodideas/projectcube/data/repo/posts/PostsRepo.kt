package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.comments.CreateCommentReq
import com.goodideas.projectcube.data.dto.comments.CreateCommentRes
import com.goodideas.projectcube.data.dto.comments.DeleteCommentRes
import com.goodideas.projectcube.data.dto.comments.UpdateCommentRes
import com.goodideas.projectcube.data.dto.posts.*
import com.goodideas.projectcube.data.dto.vote.VoteRes
import com.goodideas.projectcube.data.dto.vote.VoteState
import com.goodideas.projectcube.data.dto.vote.VoteType
import com.goodideas.projectcube.data.network.ApiService
import okhttp3.MultipartBody
import retrofit2.Response

class PostsRepo(private val source: ApiService) : IPostsRepo {

    //posts---------------------------------------
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


    //comments---------------------------------------
    override suspend fun createComment(
        postId: Int,
        req: CreateCommentReq
    ): Response<CreateCommentRes> {
        return source.retrofit.createComment(postId, req)
    }

    override suspend fun updateComment(
        commentId: Int,
        req: CreateCommentReq
    ): Response<UpdateCommentRes> {
        return source.retrofit.updateComment(commentId, req)
    }

    override suspend fun deleteComment(commentId: Int): Response<DeleteCommentRes> {
        return source.retrofit.deleteComment(commentId)
    }

    //vote
    override suspend fun vote(
        voteType: VoteType,
        id: Int,
        voteState: VoteState
    ): Response<VoteRes> {
        return source.retrofit.vote(voteType.typeName, id, voteState.stateName)
    }
}