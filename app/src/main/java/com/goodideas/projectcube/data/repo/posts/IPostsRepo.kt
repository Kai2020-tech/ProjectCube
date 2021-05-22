package com.goodideas.projectcube.data.repo.posts

import com.goodideas.projectcube.data.dto.comments.CreateCommentReq
import com.goodideas.projectcube.data.dto.comments.CreateCommentRes
import com.goodideas.projectcube.data.dto.comments.DeleteCommentRes
import com.goodideas.projectcube.data.dto.comments.UpdateCommentRes
import com.goodideas.projectcube.data.dto.posts.*
import com.goodideas.projectcube.data.dto.vote.VoteRes
import com.goodideas.projectcube.data.dto.vote.VoteState
import com.goodideas.projectcube.data.dto.vote.VoteType
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Part

interface IPostsRepo {

    //posts---------------------------------------
    suspend fun getAllPosts(): Response<AllPosts>

    suspend fun getSinglePost(id: Int): Response<SinglePostRes>

    suspend fun createPost(
        title: MultipartBody.Part?,
        content: MultipartBody.Part?,
        image: MultipartBody.Part?
    ): Response<CreatePostRes>

    suspend fun updatePost(
        id: Int,
        title: MultipartBody.Part?,
        content: MultipartBody.Part?,
        image: MultipartBody.Part?
    ): Response<UpdatePostRes>

    suspend fun deletePost(id: Int): Response<DeletePostRes>

    suspend fun searchPost(keyword: String): Response<AllPosts>


    //comments---------------------------------------
    suspend fun createComment(postId: Int, req: CreateCommentReq): Response<CreateCommentRes>

    suspend fun updateComment(commentId: Int, req: CreateCommentReq): Response<UpdateCommentRes>

    suspend fun deleteComment(commentId: Int): Response<DeleteCommentRes>

    //vote
    suspend fun vote(voteType: VoteType, id: Int, voteState: VoteState): Response<VoteRes>
}