package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

abstract class BaseResultsPage<out T>(
    val id: Int? = null,
    val page: Int? = null,
    @Json(name = "total_pages") val totalPages: Int? = null,
    @Json(name = "total_results") val totalResults: Int? = null,
    val results: List<T>? = null
)
