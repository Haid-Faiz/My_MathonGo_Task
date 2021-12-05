package com.example.datasource.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Option(
    @Json(name = "id")
    var id: String,
    @Json(name = "image")
    var image: String?,
    @Json(name = "isCorrect")
    var isCorrect: Boolean,
    @Json(name = "text")
    var text: String
)
