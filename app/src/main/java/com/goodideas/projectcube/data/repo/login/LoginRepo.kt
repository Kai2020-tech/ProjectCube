package com.goodideas.projectcube.data.repo.login

import com.goodideas.projectcube.data.dto.auth.LoginReq
import com.goodideas.projectcube.data.dto.auth.LoginRes
import com.goodideas.projectcube.data.network.ApiService
import retrofit2.Response

class LoginRepo(private val source: ApiService) : ILoginRepo {
    override suspend fun login(email: String, pwd: String): Response<LoginRes> {
        return source.retrofit.login(LoginReq(email,pwd))
    }
}