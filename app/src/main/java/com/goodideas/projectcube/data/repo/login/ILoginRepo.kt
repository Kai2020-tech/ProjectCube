package com.goodideas.projectcube.data.repo.login

import com.goodideas.projectcube.data.dto.auth.LoginRes
import com.goodideas.projectcube.data.dto.auth.LogoutRes
import retrofit2.Response

interface ILoginRepo {
    suspend fun login(email: String, pwd: String): Response<LoginRes>
    suspend fun logout(): Response<LogoutRes>
}