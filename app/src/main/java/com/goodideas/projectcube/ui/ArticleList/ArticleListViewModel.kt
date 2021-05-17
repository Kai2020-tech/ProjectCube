package com.goodideas.projectcube.ui.ArticleList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.network.ApiService
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class ArticleListViewModel(private val repo: IPostsRepo) : ViewModel() {

    val allPostsList: MutableLiveData<AllPosts> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch {
            allPostsList.value = repo.getPosts().body()
            Timber.d("${allPostsList.value}")
        }
    }
}