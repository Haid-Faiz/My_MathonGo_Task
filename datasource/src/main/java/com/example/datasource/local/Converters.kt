package com.example.datasource.local

import androidx.room.TypeConverter
import com.example.datasource.remote.models.Option
import com.example.datasource.remote.models.Question
import com.example.datasource.remote.models.Solution
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    // We need type converters for Room because room doesn't understand custom type...
    // it only knows primitive types

    val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    fun questionToString(question: Question): String {
        return moshi.adapter<Question>(Question::class.java).toJson(question)
    }

    @TypeConverter
    fun stringToQuestion(string: String): Question? {
        return moshi.adapter<Question>(Question::class.java).fromJson(string)
    }

    @TypeConverter
    fun solutionToString(solution: Solution): String {
        return moshi.adapter<Solution>(Solution::class.java).toJson(solution)
    }

    @TypeConverter
    fun stringToSolution(string: String): Solution? {
        return moshi.adapter<Solution>(Solution::class.java).fromJson(string)
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        return moshi.adapter<List<String>>(type).toJson(list)
    }

    @TypeConverter
    fun stringToList(string: String): List<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        return moshi.adapter<List<String>>(type).fromJson(string)
    }

    @TypeConverter
    fun optionListToString(optionList: List<Option>): String {
        val type = Types.newParameterizedType(List::class.java, Option::class.java)
        return moshi.adapter<List<Option>>(type).toJson(optionList)
    }

    @TypeConverter
    fun stringToOptionList(string: String): List<Option>? {
        val type = Types.newParameterizedType(List::class.java, Option::class.java)
        return moshi.adapter<List<Option>>(type).fromJson(string)
    }
}
