package com.example.datasource.remote.apis

import com.example.datasource.remote.models.QuestionItem
import retrofit2.Response
import retrofit2.http.GET

interface MathonGoApi {

    @GET("JEE_Main_-_Gravitation.json?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211206%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211206T060656Z&X-Amz-Expires=86400&X-Amz-Signature=6378aa5256624e23863870d06188185b3e2dda26545a89d8be1564c6378f75ca&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22JEE%2520Main%2520-%2520Gravitation.json%22&x-id=GetObject")
    suspend fun fetchQuestionsList(): Response<List<QuestionItem>>
}
