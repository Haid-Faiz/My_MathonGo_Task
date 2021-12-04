package com.example.my_mathongo_task.data

class QuestionsRepo constructor(

) : BaseRepo() {

    val api = com.example.datasource.remote.ApiClient().retrofit.create(com.example.datasource.remote.apis.MathonGoApi::class.java)

    suspend fun getQuestionList() = safeApiCall { api.fetchQuestionsList() }
}