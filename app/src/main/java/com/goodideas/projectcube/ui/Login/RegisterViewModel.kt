package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.network.ResResult
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel(
    private val repo: IRegisterRepo
) : ViewModel() {


    val RegisterResult: MutableLiveData<Boolean> = MutableLiveData()

    fun register(name: String, email: String, pwd: String, confirmPwd: String) {
        Timber.d("repo.register")
        viewModelScope.launch {
            val response = repo.register(name, email, pwd, confirmPwd)

            RegisterResult.value = response.isSuccessful

            Timber.d("$response")
        }
    }

}