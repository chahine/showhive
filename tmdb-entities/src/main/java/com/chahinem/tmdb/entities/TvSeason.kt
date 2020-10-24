package com.chahinem.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TvSeason(
    val id: Int? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "season_number") val seasonNumber: Int? = null,
    val _id: String? = null,
    // val air_date: ZonedDateTime? = null,
    val episodes: List<TvEpisode>? = null,
    val name: String? = null,
    val overview: String? = null,
    val credits: Credits? = null,
    val images: Images? = null,
    val videos: Videos? = null,
    @Json(name = "external_ids") val externalIds: TvSeasonExternalIds? = null
)
