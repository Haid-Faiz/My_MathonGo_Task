package com.example.datasource.remote.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "questions_table")
@JsonClass(generateAdapter = true)
data class QuestionItem(
    @PrimaryKey
    @Json(name = "id")
    var id: String,
    @Json(name = "exams")
    var exams: List<String>,
    @Json(name = "options")
    var options: List<Option>,
    @Json(name = "previousYearPapers")
    var previousYearPapers: List<String>,
    @Json(name = "question")
    var question: Question,
    @Json(name = "solution")
    var solution: Solution,
    @Json(name = "type")
    var type: String,
    //    @Json(name = "subjects")
    //    var subjects: List<String>,
    //    @Json(name = "topics")
    //    var topics: List<Any>,
    //    @Json(name = "level")
    //    var level: Any?,
    //    @Json(name = "correctValue")
    //    var correctValue: Any?,
    //    @Json(name = "approximateTimeRequired")
    //    var approximateTimeRequired: Any?,
    //    @Json(name = "category")
    //    var category: Any?,
    //    @Json(name = "chapters")
    //    var chapters: List<String>,
    //    @Json(name = "classes")
    //    var classes: List<Any>,
    //    @Json(name = "concepts")
    //    var concepts: List<Any>,
    //    @Json(name = "helperText")
    //    var helperText: Any?,
    //    @Json(name = "questionLabels")
    //    var questionLabels: List<Any>,
    //    @Json(name = "source")
    //    var source: String,
    //    @Json(name = "imageBaseUrl")
    //    var imageBaseUrl: Any?,
    //    @Json(name = "microConcepts")
    //    var microConcepts: List<Any>,
    //    @Json(name = "videoSolution")
    //    var videoSolution: Any?
)
