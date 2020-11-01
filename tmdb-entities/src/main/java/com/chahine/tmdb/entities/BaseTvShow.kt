package com.chahine.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseTvShow(
    val id: Int? = null,
    @Json(name = "original_name") val originalName: String? = null,
    @Json(name = "original_language") val originalLanguage: String? = null,
    val overview: String? = null,
    val name: String? = null,
    @Json(name = "origin_country") val originCountry: List<String>? = null,
    @Json(name = "genre_ids") val genreIds: List<Int>? = null,
    // @Json(name = "first_air_date") val firstAirDate: ZonedDateTime? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    val popularity: Double? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
    @Json(name = "vote_count") val voteCount: Int? = null,
    val rating: Int? = null,
    @Json(name = "media_type") val mediaType: String? = null
)
