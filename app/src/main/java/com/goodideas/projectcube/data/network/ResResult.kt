package com.goodideas.projectcube.data.network

sealed class ResResult<T> {
    data class Success<T>(val data: T) : ResResult<T>()
    data class Fail<T>(val error: String?=null, val errorResId: Int? = null) : ResResult<T>()
}