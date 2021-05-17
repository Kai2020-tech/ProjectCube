package com.goodideas.projectcube.ui.CreatePost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.posts.CreatePostReq
import com.goodideas.projectcube.data.network.ApiService
import kotlinx.coroutines.launch

class CreatePostViewModel:ViewModel() {
    fun createPost(t:String,c:String){
        viewModelScope.launch {
            ApiService.retrofit.createPost(CreatePostReq(t,c))
        }
    }
}