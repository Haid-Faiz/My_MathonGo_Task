package com.example.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datasource.remote.models.QuestionItem

@Dao
interface QuestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionItem: QuestionItem)

    @Query("SELECT * FROM questions_table")
    fun getAttemptedQuestions(): LiveData<List<QuestionItem>>
}
