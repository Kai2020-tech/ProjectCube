package com.goodideas.projectcube.ui.updatePost

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UpdatePostViewModel(private val repo: IPostsRepo) : ViewModel() {

    val updatePostResult: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.BEFORE)

    //中:存view的id跟內容
    val contentList = mutableListOf<Pair<Int,String>>()


    private fun getPhoto(imageUri: Uri?): MultipartBody.Part? {
        val file = File(imageUri?.path ?: "")
        return if (file.exists()) {
            val requestFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", file.name, requestFile)
        } else null
    }


    fun UpdatePost(
        id: Int,
        t: MultipartBody.Part,
        c: MultipartBody.Part,
        imageUri: Uri?
    ) {
        viewModelScope.launch {
            val response = repo.updatePost(id, t, c, getPhoto(imageUri))
            if (response.isSuccessful){
                updatePostResult.value = ResponseStatus.SUCCESS
            }else{
                updatePostResult.value = ResponseStatus.FAIL
            }
        }
    }
}