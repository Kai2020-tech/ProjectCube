package com.goodideas.projectcube.data.network

import com.goodideas.projectcube.data.dto.register.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): ResResult<T> {
    return withContext(dispatcher) {
        try {
            val apiResult = apiCall.invoke()

            ResResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResResult.Fail("${throwable.message} IOException : Network error !!")
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    ResResult.Fail(errorResponse?.message ?: "")
                }
                else -> {
                    ResResult.Fail("other error!!")
                }

            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.string()?.let {
            Gson().fromJson(it, ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}

private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
    return try {
        errorBody?.string()?.let {
            Gson().fromJson(it, ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}