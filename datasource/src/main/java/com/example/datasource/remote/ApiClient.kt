package com.example.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val API_URL =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/3e55add8-7606-43fb-b739-49d6e57b1383/JEE_Main_-_Gravitation.json?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211204%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211204T045759Z&X-Amz-Expires=86400&X-Amz-Signature=e26560d90f9193316d28200ac17274562df999bab3df5918195f31281a3b8686&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22JEE%2520Main%2520-%2520Gravitation.json%22&x-id=GetObject"

    private val okHttpBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .callTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

}