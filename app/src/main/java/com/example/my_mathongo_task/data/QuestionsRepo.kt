package com.example.my_mathongo_task.data

import androidx.lifecycle.LiveData
import com.example.datasource.local.QuestionsDao
import com.example.datasource.remote.apis.MathonGoApi
import com.example.datasource.remote.models.QuestionItem
import com.example.my_mathongo_task.utils.AppPrefs
import com.example.my_mathongo_task.utils.Resource
import javax.inject.Inject

class QuestionsRepo @Inject constructor(
    private val mathonGoApi: MathonGoApi,
    private val appPrefs: AppPrefs,
    private val questionsDao: QuestionsDao
) : BaseRepo() {

    suspend fun getQuestionList(): Resource<List<QuestionItem>> = safeApiCall {
        mathonGoApi.fetchQuestionsList()
    }

    fun getAttemptedQuestions(): LiveData<List<QuestionItem>> = questionsDao.getAttemptedQuestions()

    suspend fun insertQuestion(questionItem: QuestionItem) = questionsDao.insert(questionItem)

    suspend fun saveQuestionListType(questionListType: String) =
        appPrefs.saveQuestionsListType(questionListType)

    fun getQuestionListType() = appPrefs.getQuestionsListType()
}