package com.goodideas.projectcube.ui.ReadArticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.dto.comments.CreateCommentReq
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import com.goodideas.projectcube.data.dto.vote.VoteState
import com.goodideas.projectcube.data.dto.vote.VoteType
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class ArticleDetailViewModel(private val repo: IPostsRepo) : ViewModel() {

    private val _singlePostContent = MutableLiveData<SinglePostRes>()
    val singlePostContent: LiveData<SinglePostRes>
        get() = _singlePostContent

    private val _deletePostStatus = MutableLiveData<ResponseStatus>()
    val deletePostStatus: LiveData<ResponseStatus> = _deletePostStatus


    fun getSinglePost(postId: Int) {
        viewModelScope.launch {
            Timber.d("getSinglePost $postId")
            val response = repo.getSinglePost(postId)
            if (response.isSuccessful) {
                _singlePostContent.postValue(response.body())
                Timber.d("${response.body()}")
            }
        }
    }

    fun createComment(postId: Int, content: String) {
        viewModelScope.launch {
            val response = repo.createComment(postId, CreateCommentReq(content))
            if (response.isSuccessful) {
                // TODO: 2021/5/21
            } else {
                // TODO: 2021/5/21
            }
        }
    }

    fun updateComment(commentId: Int, content: String) {
        viewModelScope.launch {
            val response = repo.updateComment(commentId, CreateCommentReq(content))
            if (response.isSuccessful) {
                // TODO: 2021/5/21
            } else {
                // TODO: 2021/5/21
            }
        }
    }

    fun deleteComment(commentId: Int) {
        viewModelScope.launch {
            val response = repo.deleteComment(commentId)
            if (response.isSuccessful) {
                // TODO: 2021/5/21
            } else {
                // TODO: 2021/5/21
            }
        }
    }

    fun vote(voteType: VoteType, id: Int, voteState: VoteState) {
        viewModelScope.launch {
            VoteType.values()
            val response = repo.vote(voteType, id, voteState)
            if (response.isSuccessful) {
                // TODO: 2021/5/21
            } else {
                // TODO: 2021/5/21
            }
        }
    }

    fun deletePost(postId: Int) {
        _deletePostStatus.value = ResponseStatus.BEFORE
        viewModelScope.launch {
            val response = repo.deletePost(postId)
            _deletePostStatus.value = ResponseStatus.LOADING
            if (response.isSuccessful) {
                _deletePostStatus.value = ResponseStatus.SUCCESS
                Timber.d("delete ok")
            } else {
                _deletePostStatus.value = ResponseStatus.FAIL
            }
        }
    }
}