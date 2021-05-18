package com.goodideas.projectcube.ui.SearchPost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.dto.posts.SearchPostRes
import com.goodideas.projectcube.data.dto.posts.SearchPostResItem
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import kotlinx.coroutines.launch

class SearchViewModel(private val repo: PostsRepo) : ViewModel() {

    val searchResult: MutableLiveData<SearchPostRes> = MutableLiveData()
    val searchResultStatus: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.BEFORE)

    fun searchPost(keyword: String) {
        viewModelScope.launch {

            searchResultStatus.value = ResponseStatus.LOADING

            val response = repo.searchPost(keyword)
            if (response.isSuccessful) {
                searchResult.value = response.body()
                searchResultStatus.value = ResponseStatus.SUCCESS
            } else {
                searchResult.value = null
                searchResultStatus.value = ResponseStatus.FAIL
            }
        }
    }
}