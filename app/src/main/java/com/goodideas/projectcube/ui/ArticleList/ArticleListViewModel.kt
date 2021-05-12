package com.goodideas.projectcube.ui.ArticleList

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.posts.Posts
import com.goodideas.projectcube.data.network.Api
import com.goodideas.projectcube.data.network.ApiService
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

//this work, but why do you choose interface as constructure
class ArticleListViewModel(private val repo: IPostsRepo) : ViewModel() {

    val postsList: MutableLiveData<Posts> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch {
            postsList.value = repo.getPosts().body()
            Timber.d("${postsList.value}")
        }
    }

}