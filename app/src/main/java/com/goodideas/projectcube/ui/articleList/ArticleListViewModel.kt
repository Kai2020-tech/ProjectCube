package com.goodideas.projectcube.ui.articleList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.posts.AllPosts
import com.goodideas.projectcube.data.dto.vote.VoteState
import com.goodideas.projectcube.data.dto.vote.VoteType
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class ArticleListViewModel(private val repo: IPostsRepo) : ViewModel() {

    val allPostsList: MutableLiveData<AllPosts> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch {
            allPostsList.value = repo.getAllPosts().body()
//            Timber.d("${allPostsList.value}")
        }
    }

    fun vote(voteType: VoteType, id:Int, voteState: VoteState){
        viewModelScope.launch {
            VoteType.values()
            val response = repo.vote(voteType,id,voteState)
            if (response.isSuccessful) {
                // TODO: 2021/5/21
            }else{
                // TODO: 2021/5/21
            }
        }
    }
}