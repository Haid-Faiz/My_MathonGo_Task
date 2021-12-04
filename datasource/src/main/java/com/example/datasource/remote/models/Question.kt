package com.example.datasource.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Question(
    @Json(name = "image")
    var image: String?,
    @Json(name = "text")
    var text: String
)