package com.goodideas.projectcube.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.data.dto.profile.ProfileRes
import com.goodideas.projectcube.data.repo.profile.IProfileRepo
import kotlinx.coroutines.launch

class ProfileViewModel(val repo: IProfileRepo) : ViewModel() {

    private val _userProfile: MutableLiveData<ProfileRes> = MutableLiveData<ProfileRes>()
    val userProfile: LiveData<ProfileRes>
        get() = _userProfile


    fun getUserProfile(userId: Int) {
        viewModelScope.launch {
            val response = repo.getUserProfile(userId)
            if (response.isSuccessful) {
                _userProfile.value = response.body()
                // TODO: 2021/5/21
            } else {
                // TODO: 2021/5/21
            }
        }
    }
}