package com.goodideas.projectcube.data.repo.register

import com.goodideas.projectcube.data.dto.register.RegisterRes
import com.goodideas.projectcube.data.network.ResResult
import retrofit2.Response

interface IRegisterRepo {
    suspend fun register(
        name: String,
        email: String,
        pwd: String,
        confirmPwd: String
    ): ResResult<RegisterRes>
}