package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.register.ErrorResponse
import com.goodideas.projectcube.data.network.token
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel(
    private val repo: IRegisterRepo
) : ViewModel() {


    val registerResult: MutableLiveData<Boolean> = MutableLiveData()
    var registerMessage = ""

    fun register(name: String, email: String, pwd: String, confirmPwd: String) {
        Timber.d("repo.register")
        viewModelScope.launch {
            val response = repo.register(name, email, pwd, confirmPwd)
            if (response.isSuccessful) {
                registerResult.value = true
                token = response.body()?.token ?: "no token"
                Timber.d(token)
                registerMessage = token
            } else {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                registerResult.value = false
                registerMessage = errorResponse.message
                Timber.d(errorResponse.message)
            }
        }
    }

}