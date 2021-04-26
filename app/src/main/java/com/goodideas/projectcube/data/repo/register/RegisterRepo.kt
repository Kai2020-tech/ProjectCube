package com.goodideas.projectcube.data.repo.register

import android.util.Log
import com.goodideas.projectcube.data.dto.register.RegisterReq
import com.goodideas.projectcube.data.dto.register.RegisterRes
import com.goodideas.projectcube.data.network.ApiService
import com.goodideas.projectcube.data.network.ResResult
import com.goodideas.projectcube.data.network.safeApiCall
import com.goodideas.projectcube.data.network.token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

class RegisterRepo(private val source: ApiService) : IRegisterRepo {
    override suspend fun register(
        name: String,
        email: String,
        pwd: String,
        confirmPwd: String
    ): ResResult<RegisterRes> {
//        val resResult = source.retrofit.register(RegisterReq(name, email, pwd, confirmPwd))
        return safeApiCall {
            Timber.d("api register")
            source.retrofit.register(RegisterReq(name, email, pwd, confirmPwd)).body()!!
        }.apply {
            if (this is ResResult.Success) {
                token = this.data.token
            }
        }
    }
}