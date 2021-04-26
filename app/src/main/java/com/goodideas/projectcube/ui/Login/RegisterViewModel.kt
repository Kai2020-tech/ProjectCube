package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.register.ErrorResponse
import com.goodideas.projectcube.data.network.ResResult
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel(
    private val repo: IRegisterRepo
) : ViewModel() {


    val registerResult: MutableLiveData<Boolean> = MutableLiveData()

    fun register(name: String, email: String, pwd: String, confirmPwd: String) {
        Timber.d("repo.register")
        viewModelScope.launch {
            val response = repo.register(name, email, pwd, confirmPwd)

            registerResult.value = response.isSuccessful

            val gson = Gson()
            val type = object : TypeToken<ErrorResponse>() {}.type
            var errorResponse: ErrorResponse = gson.fromJson(response.errorBody()!!.charStream(), type)

            Timber.d("${response.body()}")
            Timber.d(errorResponse.message)
        }
    }

}