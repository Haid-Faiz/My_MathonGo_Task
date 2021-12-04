package com.example.my_mathongo_task.data

import com.example.datasource.remote.ApiClient
import com.example.datasource.remote.models.QuestionItem
import com.example.my_mathongo_task.utils.Resource

class QuestionsRepo constructor(

) : BaseRepo() {

    val api =
        ApiClient().retrofit.create(com.example.datasource.remote.apis.MathonGoApi::class.java)

    suspend fun getQuestionList(): Resource<List<QuestionItem>> = safeApiCall {
        api.fetchQuestionsList()
    }
}