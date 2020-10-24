package com.chahinem.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AccountStates(
    val id: Int,
    val rated: Boolean,
    val rating: RatingObject,
    val favorite: Boolean? = null,
    @Json(name = "episode_number") val episodeNumber: Int? = null,
    val watchlist: Boolean? = null
)
