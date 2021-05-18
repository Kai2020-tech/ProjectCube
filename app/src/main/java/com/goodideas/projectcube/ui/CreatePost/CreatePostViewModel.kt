package com.goodideas.projectcube.ui.CreatePost

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.network.ApiService
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CreatePostViewModel : ViewModel() {


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
            ApiService.retrofit.createPost(t, c, getPhoto(imageUri))
        }
    }
}