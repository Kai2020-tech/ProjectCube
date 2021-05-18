package com.goodideas.projectcube.data.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = " http://api.rrrui.site/api/"
//private const val BASE_URL = " http://54.249.177.151/api/"

var token = ""

class ApiService {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

//    val gson = GsonBuilder().setLenient().create()

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addNetworkInterceptor(HeaderInterceptor())
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofit: Api = retrofitBuilder.create(
        Api::class.java
    )
}