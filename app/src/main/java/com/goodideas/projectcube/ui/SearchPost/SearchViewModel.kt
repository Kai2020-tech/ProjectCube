package com.goodideas.projectcube.ui.SearchPost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import kotlinx.coroutines.launch

class SearchViewModel(private val repo: IPostsRepo) : ViewModel() {

    val searchResult: MutableLiveData<AllPosts> = MutableLiveData()
    private val searchResultStatus: MutableLiveData<ResponseStatus> =
        MutableLiveData(ResponseStatus.BEFORE)

    fun searchPost(keyword: String) {
        viewModelScope.launch {

            searchResultStatus.value = ResponseStatus.LOADING

            val response = repo.searchPost(keyword)
            if (response.isSuccessful) {
                searchResult.value = response.body()
                searchResultStatus.value = ResponseStatus.SUCCESS
            } else {
                searchResult.value = AllPosts()
                searchResultStatus.value = ResponseStatus.FAIL
            }
        }
    }

}