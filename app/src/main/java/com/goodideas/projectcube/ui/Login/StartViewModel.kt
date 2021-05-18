package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.register.ErrorResponse
import com.goodideas.projectcube.data.network.ApiService
import com.goodideas.projectcube.data.network.token
import com.goodideas.projectcube.data.repo.login.ILoginRepo
import com.goodideas.projectcube.data.repo.login.LoginRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import timber.log.Timber
enum class ResponseStatus{SUCCESS, FAIL, LOADING,BEFORE}
class StartViewModel(
    private val repo: LoginRepo
) : ViewModel() {

    val loginResult: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.BEFORE)

    fun initLoginResult(){
        loginResult.postValue(ResponseStatus.BEFORE)
    }
    fun login(email: String, pwd: String) {
        Timber.d("login")
//        viewModelScope.launch {
//            val response = repo.login(email, pwd)
//            if (response.isSuccessful) {
//                loginResult.value = true
//                token = response.body()?.token ?: "no token"
//                Timber.d("login success. $token")
////                getUserProfile()
//                //why logout
////                logout()
//            } else {
//                loginResult.value = false
//                Timber.d("login fail.")
//            }
//        }
        viewModelScope.launch {
            val response = repo.login(email, pwd)
            loginResult.value = ResponseStatus.LOADING
            if (response.isSuccessful) {
                loginResult.value = ResponseStatus.SUCCESS
                token = response.body()?.token ?: "no token"
                Timber.d("login success. $token")
            } else {
                loginResult.value = ResponseStatus.FAIL
                Timber.d("login fail.")
            }
        }
    }

    //below fun just for test

//    fun getUserProfile(){
//        viewModelScope.launch {
//            val response = ApiService.retrofit.getProfile()
//            if (response.isSuccessful){
//                Timber.d("user profile is ${response.body()}")
//            }else{
//                Timber.d("getUserProfile fail.")
//            }
//        }
//    }
//
//    fun logout(){
//        viewModelScope.launch {
//            val response = ApiService.retrofit.logout()
//            Timber.d("${response.body()}")
//        }
//    }



}