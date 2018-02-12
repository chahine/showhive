package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class TvExternalIds(
    @Json(name = "freebase_id") val freebaseId: String? = null,
    @Json(name = "freebase_mid") val freebaseMid: String? = null,
    val id: Int? = null,
    @Json(name = "tvrage_id") val tvrageId: Int? = null,
    @Json(name = "imdb_id") val imdbId: String? = null,
    @Json(name = "tvdb_id") val tvdbId: Int? = null
)
