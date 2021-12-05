package com.example.datasource.remote.apis

import com.example.datasource.remote.models.QuestionItem
import retrofit2.Response
import retrofit2.http.GET

interface MathonGoApi {

    @GET("JEE_Main_-_Gravitation.json?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211205%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211205T052322Z&X-Amz-Expires=86400&X-Amz-Signature=89a172313007503854d239bbb425b9e792921471c04ae543b3d80018f48c2fec&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22JEE%2520Main%2520-%2520Gravitation.json%22&x-id=GetObject")
    suspend fun fetchQuestionsList(): Response<List<QuestionItem>>
}