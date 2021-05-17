package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.register.ErrorResponse
import com.goodideas.projectcube.data.network.ApiService
import com.goodideas.projectcube.data.network.token
import com.goodideas.projectcube.data.repo.login.ILoginRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import timber.log.Timber

class StartViewModel(
    private val repo: ILoginRepo
) : ViewModel() {


    val loginResult: MutableLiveData<Boolean> = MutableLiveData()

    fun login(email: String, pwd: String) {
        Timber.d("login")
        viewModelScope.launch {
            val response = repo.login(email, pwd)
            if (response.isSuccessful) {
                loginResult.value = true
                token = response.body()?.token ?: "no token"
                Timber.d("login success. $token")
                getUserProfile()
                logout()
            } else {
//                val gson = Gson()
//                val type = object : TypeToken<ErrorResponse>() {}.type
//                val errorResponse: ErrorResponse =
//                    gson.fromJson(response.errorBody()!!.charStream(), type)
//                Timber.d("error%s", errorResponse.message)
                loginResult.value = false
                Timber.d("login fail.")
            }
        }
    }

    //below fun just for test

    fun getUserProfile(){
        viewModelScope.launch {
            val response = ApiService.retrofit.getProfile()
            if (response.isSuccessful){
                Timber.d("user profile is ${response.body()}")
            }else{
                Timber.d("getUserProfile fail.")
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            val response = ApiService.retrofit.logout()
            Timber.d("${response.body()}")
        }
    }



}