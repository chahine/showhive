package com.chahinem.tmdb.entities

open class BaseTvShow(
    val id: Int? = null,
    val original_name: String? = null,
    val original_language: String? = null,
    val overview: String? = null,
    val name: String? = null,
    val origin_country: List<String>? = null,
    val genre_ids: List<Int>? = null,
//    val first_air_date: ZonedDateTime? = null,
    val backdrop_path: String? = null,
    val poster_path: String? = null,
    val popularity: Double? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,

    val rating: Int? = null,
    val media_type: String? = null
)
