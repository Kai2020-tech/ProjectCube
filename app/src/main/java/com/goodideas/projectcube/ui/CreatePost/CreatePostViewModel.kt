package com.goodideas.projectcube.ui.CreatePost

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
class CreatePostViewModel(private val repo: PostsRepo) : ViewModel() {

    val createPostResult: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.BEFORE)


    private fun getPhoto(imageUri: Uri?): MultipartBody.Part? {
        val file = File(imageUri?.path ?: "")
        return if (file.exists()) {
            val requestFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", file.name, requestFile)
        } else null
    }

    fun createPost(
        t: MultipartBody.Part,
        c: MultipartBody.Part,
        imageUri: Uri?
    ) {
        viewModelScope.launch {
            val response = repo.createPost(t, c, getPhoto(imageUri))
            if (response.isSuccessful){
                createPostResult.value = ResponseStatus.SUCCESS
            }else{
                createPostResult.value = ResponseStatus.FAIL
            }
        }
    }
}