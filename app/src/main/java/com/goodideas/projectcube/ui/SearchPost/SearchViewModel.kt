package com.goodideas.projectcube.ui.SearchPost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.dto.posts.AllPostsItem
import com.goodideas.projectcube.data.dto.posts.SearchPostRes
import com.goodideas.projectcube.data.dto.posts.SearchPostResItem
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import kotlinx.coroutines.launch

class SearchViewModel(private val repo: IPostsRepo) : ViewModel() {

    val searchResult: MutableLiveData<AllPosts> = MutableLiveData()
    private val searchResultStatus: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.BEFORE)

    fun searchPost(keyword: String) {
        viewModelScope.launch {

            searchResultStatus.value = ResponseStatus.LOADING

            val response = repo.searchPost(keyword)
            if (response.isSuccessful) {
                searchResult.value = response.body()?.SearchToArticle()
                searchResultStatus.value = ResponseStatus.SUCCESS
            } else {
                searchResult.value = null
                searchResultStatus.value = ResponseStatus.FAIL
            }
        }
    }

    private fun SearchPostRes.SearchToArticle(): AllPosts {
        val a = AllPosts()
        this.forEach {
            val (content, date, id,image,title,update,userId) = it
            a.add(AllPostsItem("", Int.MIN_VALUE,content?:"",date?:"",
                Int.MIN_VALUE,id?: Int.MIN_VALUE,image?:"", Int.MIN_VALUE,"",
                title?:"",update?:"",userId?: Int.MIN_VALUE))
        }
        return a
    }
}