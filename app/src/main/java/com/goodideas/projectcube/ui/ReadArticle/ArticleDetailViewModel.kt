package com.goodideas.projectcube.ui.ReadArticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import com.goodideas.projectcube.data.network.ApiService
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class ArticleDetailViewModel() : ViewModel() {

    private val _singlePostContent = MutableLiveData<SinglePostRes>()
    val singlePostContent:LiveData<SinglePostRes>
        get() = _singlePostContent

    fun getSinglePost(postId: Int){
        viewModelScope.launch {
            val response = ApiService.retrofit.getSinglePost(postId)
            _singlePostContent.postValue(response.body())
            Timber.d("${response.body()}")
        }
    }
}