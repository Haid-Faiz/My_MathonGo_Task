package com.example.my_mathongo_task.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.remote.models.QuestionItem
import com.example.my_mathongo_task.data.QuestionsRepo
import com.example.my_mathongo_task.utils.Resource
import kotlinx.coroutines.launch

class QuestionsViewModel constructor() : ViewModel() {

    private val questionsRepo = QuestionsRepo()
    private lateinit var questionList: List<QuestionItem>
    private var currentQuestionNum: Int = 0

    private val _questions = MutableLiveData<Resource<List<QuestionItem>>>()
    val questions: LiveData<Resource<List<QuestionItem>>> = _questions

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() = viewModelScope.launch {
        _questions.postValue(Resource.Loading())
        _questions.postValue(questionsRepo.getQuestionList())
    }

    private fun getAttemptedQuestions() = viewModelScope.launch {
        _questions.postValue(Resource.Loading())
        _questions.postValue(questionsRepo.getQuestionList())
    }


    fun setQuestionList(questionList: List<QuestionItem>) {
        this.questionList = questionList
    }

    fun getQuestionList(): List<QuestionItem> {
        return this.questionList
    }

    fun setCurrentQuestionNum(currentQuesNum: Int) {
        currentQuestionNum = currentQuesNum
    }

    fun getCurrentQuestionNum(): Int {
        return currentQuestionNum
    }


}