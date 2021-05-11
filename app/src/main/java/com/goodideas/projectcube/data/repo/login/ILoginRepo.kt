package com.goodideas.projectcube.data.repo.login

import com.goodideas.projectcube.data.dto.auth.LoginRes
import retrofit2.Response

interface ILoginRepo {
    suspend fun login(email: String, pwd: String): Response<LoginRes>
}