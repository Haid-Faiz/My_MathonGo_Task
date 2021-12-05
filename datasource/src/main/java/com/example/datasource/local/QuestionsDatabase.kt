package com.example.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.datasource.remote.models.QuestionItem

private val QUESTION_DATABASE_NAME = "attempted_questions_database"

@Database(entities = [QuestionItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class QuestionsDatabase : RoomDatabase() {

    // fun that return dao object
    abstract fun getQuestionsDao(): QuestionsDao

    companion object {

        @Volatile
        private var instance: QuestionsDatabase? = null

        operator fun invoke(context: Context): QuestionsDatabase = instance ?: synchronized(this) {
            createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): QuestionsDatabase = Room.databaseBuilder(
            context.applicationContext,
            QuestionsDatabase::class.java,
            QUESTION_DATABASE_NAME
        ).build()
    }
}
