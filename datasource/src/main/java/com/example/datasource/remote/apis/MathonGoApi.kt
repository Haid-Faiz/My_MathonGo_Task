package com.example.datasource.remote.apis

import com.example.datasource.remote.models.QuestionList
import retrofit2.Response
import retrofit2.http.GET

interface MathonGoApi {

    @GET
    suspend fun fetchQuestionsList(): Response<QuestionList>
}