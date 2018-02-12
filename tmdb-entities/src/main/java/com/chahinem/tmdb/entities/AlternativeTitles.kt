package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class AlternativeTitles(
    val id: Int? = null,
    // alternate = arrayOf("results"))
    @Json(name = "titles")
    val titles: List<AlternativeTitle>? = null
)
