package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.register.ErrorResponse
import com.goodideas.projectcube.data.network.token
import com.goodideas.projectcube.data.repo.login.ILoginRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import timber.log.Timber

class StartViewModel(
    private val repo: ILoginRepo
) : ViewModel() {


    fun login(email: String, pwd: String) {
        Timber.d("repo.register")
        viewModelScope.launch {
            val response = repo.login(email, pwd)
            if (response.isSuccessful) {
                token = response.body()?.token ?: "no token"
                Timber.d("login success. $token")
            } else {
//                val gson = Gson()
//                val type = object : TypeToken<ErrorResponse>() {}.type
//                val errorResponse: ErrorResponse =
//                    gson.fromJson(response.errorBody()!!.charStream(), type)
//                Timber.d("error%s", errorResponse.message)
                Timber.d("login fail.")
            }
        }
    }
}