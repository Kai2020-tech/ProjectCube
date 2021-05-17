package com.goodideas.projectcube.ui.Register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.register.ErrorResponse
import com.goodideas.projectcube.data.network.token
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import com.goodideas.projectcube.ui.Login.ResponseStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel(
    private val repo: IRegisterRepo
) : ViewModel() {


    val registerResult: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.BEFORE)
    var registerMessage = ""
    fun initRegisterResult(){
        registerResult.postValue(ResponseStatus.BEFORE)
    }

    fun register(name: String, email: String, pwd: String, confirmPwd: String) {
        Timber.d("repo.register")
        viewModelScope.launch {
            val response = repo.register(name, email, pwd, confirmPwd)
            registerResult.value = ResponseStatus.LOADING

            if (response.isSuccessful) {
                registerResult.value = ResponseStatus.SUCCESS
                token = response.body()?.token ?: "no token"
                Timber.d("token%s", token)
                registerMessage = token
            } else {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                registerResult.value = ResponseStatus.FAIL
                registerMessage = errorResponse.message
                Timber.d("error%s", errorResponse.message)
            }
        }
    }

}