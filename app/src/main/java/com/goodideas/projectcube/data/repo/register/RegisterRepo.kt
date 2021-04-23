package com.goodideas.projectcube.data.repo.register

import android.util.Log
import com.goodideas.projectcube.data.dto.register.RegisterReq
import com.goodideas.projectcube.data.dto.register.RegisterRes
import com.goodideas.projectcube.data.network.ApiService
import com.goodideas.projectcube.data.network.ResResult
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
        val resResult = source.retrofit.register(RegisterReq(name, email, pwd, confirmPwd))
        return withContext(Dispatchers.IO) {
            try {
                if (resResult.isSuccessful) {
                    //Do something with response e.g show to the UI.
                    resResult.body()?.let {
                        ResResult.Success(it)
                    } ?: let { ResResult.Fail("error: nobody") }
                } else {
                    Timber.d("Error: ${resResult.code()}")
                    ResResult.Fail(resResult.errorBody().toString())
                }

            } catch (e: HttpException) {
                Timber.d("Exception ${e.message}")
                ResResult.Fail("${e.message}")
            } catch (e: Throwable) {
                Timber.d("Ooops: Something else went wrong")
                ResResult.Fail("${e.message}")
            }

        }
    }
}