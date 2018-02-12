package com.chahinem.tmdb.entities

class TvSeason(
    val id: Int? = null,
    val poster_path: String? = null,
    val season_number: Int? = null,
    val _id: String? = null,
//    val air_date: ZonedDateTime? = null,
    val episodes: List<TvEpisode>? = null,
    val name: String? = null,
    val overview: String? = null,
    val credits: Credits? = null,
    val images: Images? = null,
    val videos: Videos? = null,
    val external_ids: TvSeasonExternalIds? = null
)
