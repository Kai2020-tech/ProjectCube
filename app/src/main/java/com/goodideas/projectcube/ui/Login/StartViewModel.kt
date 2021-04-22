package com.goodideas.projectcube.ui.Login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.goodideas.projectcube.data.repo.auth.IAuthRepo

class StartViewModel(
    private val state: SavedStateHandle,
    private val repo: IAuthRepo
) : ViewModel() {

}