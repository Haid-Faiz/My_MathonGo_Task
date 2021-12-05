package com.example.my_mathongo_task.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.my_mathongo_task.utils.Constants.APP_DATASTORE
import com.example.my_mathongo_task.utils.Constants.QUESTION_LIST_TYPE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore by preferencesDataStore(APP_DATASTORE)

class AppPrefs(context: Context) {

    private val _datastore = context.datastore

    private val questionListTypeKey = stringPreferencesKey(QUESTION_LIST_TYPE_KEY)

    suspend fun saveQuestionsListType(questionListType: String) = _datastore.edit {
        it[questionListTypeKey] = questionListType
    }

    fun getQuestionsListType(): Flow<String?> = _datastore.data.map {
        it[questionListTypeKey]
    }
}
