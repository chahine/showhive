package com.chahine.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TvShow(
    val id: Int? = null,
    @Json(name = "original_name") val originalName: String? = null,
    @Json(name = "original_language") val originalLanguage: String? = null,
    val overview: String? = null,
    val name: String? = null,
    @Json(name = "origin_country") val originCountry: List<String>? = null,
    @Json(name = "genre_ids") val genreIds: List<Int>? = null,
    // @Json(name = "first_air_date")  val firstAirDate: ZonedDateTime? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    val popularity: Double? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
    @Json(name = "vote_count") val voteCount: Int? = null,
    val rating: Int? = null,
    @Json(name = "media_type") val mediaType: String? = null,
    @Json(name = "created_by") val createdBy: List<Person>? = null,
    val networks: List<Network>? = null,
    @Json(name = "episode_run_time") val episodeRunTime: List<Int>? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    @Json(name = "in_production") val inProduction: Boolean = false,
    val languages: List<String>? = null,
    // @Json(name = "val") val lastAirDate: ZonedDateTime? = null,
    @Json(name = "number_of_episodes") val numberOfEpisodes: Int? = null,
    @Json(name = "number_of_seasons") val numberOfSeasons: Int? = null,
    @Json(name = "production_companies") val productionCompanies: List<BaseCompany>? = null,
    val seasons: List<TvSeason>? = null,
    val status: String? = null,
    val type: String? = null,
    @Json(name = "Following") // Following are used with append_to_response
    val images: Images? = null,
    val credits: Credits? = null,
    @Json(name = "external_ids") val externalIds: TvExternalIds? = null,
    @Json(name = "alternative_titles") val alternativeTitles: AlternativeTitles? = null,
    val changes: Changes? = null,
    val keywords: Keywords? = null,
    val recommendations: TvShowResultsPage? = null,
    val translations: Translations? = null,
    @Json(name = "content_ratings") val contentRatings: ContentRatings? = null,
    val similar: TvShowResultsPage? = null,
    val videos: Videos? = null
)
