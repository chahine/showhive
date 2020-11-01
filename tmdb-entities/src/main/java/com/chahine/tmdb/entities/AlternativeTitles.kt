package com.chahine.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AlternativeTitles(
    val id: Int? = null,
    // alternate = arrayOf("results"))
    @Json(name = "titles")
    val titles: List<AlternativeTitle>? = null
)
