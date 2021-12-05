package com.example.my_mathongo_task.data

import com.example.datasource.remote.apis.MathonGoApi
import com.example.datasource.remote.models.QuestionItem
import com.example.my_mathongo_task.utils.AppPrefs
import com.example.my_mathongo_task.utils.Resource
import javax.inject.Inject

class QuestionsRepo @Inject constructor(
    private val mathonGoApi: MathonGoApi,
    private val appPrefs: AppPrefs
) : BaseRepo() {

    suspend fun getQuestionList(): Resource<List<QuestionItem>> = safeApiCall {
        mathonGoApi.fetchQuestionsList()
    }

    suspend fun saveQuestionListType(questionListType: String) =
        appPrefs.saveQuestionsListType(questionListType)

    fun getQuestionListType() = appPrefs.getQuestionsListType()
}