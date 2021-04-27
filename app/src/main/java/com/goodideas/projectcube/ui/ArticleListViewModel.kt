package com.goodideas.projectcube.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class ArticleListViewModel(private val repo: IPostsRepo) : ViewModel() {

    val postsList: MutableLiveData<Posts> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch {
            postsList.value = repo.getPosts().body()
            Timber.d("${postsList.value}")
        }
    }
}