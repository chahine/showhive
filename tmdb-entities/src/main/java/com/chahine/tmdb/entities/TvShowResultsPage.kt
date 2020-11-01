package com.chahine.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TvShowResultsPage(
    val id: Int? = null,
    val page: Int? = null,
    @Json(name = "total_pages") val totalPages: Int? = null,
    @Json(name = "total_results") val totalResults: Int? = null,
    val results: List<BaseTvShow>? = null
)
