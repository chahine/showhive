package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class Keywords(
    val id: Int? = null,

    // alternate = arrayOf("results")
    @Json(name = "keywords") val keywords: List<BaseKeyword>? = null
)
