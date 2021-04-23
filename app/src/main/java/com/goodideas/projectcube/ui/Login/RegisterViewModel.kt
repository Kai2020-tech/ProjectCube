package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.network.ResResult
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repo: IRegisterRepo
) : ViewModel() {

    val loginResult: MutableLiveData<Boolean> = MutableLiveData()

    fun register(name: String, email: String, pwd: String, confirmPwd: String) {
        viewModelScope.launch {
            loginResult.value = repo.register(name, email, pwd, confirmPwd) is ResResult.Success
        }
    }

}