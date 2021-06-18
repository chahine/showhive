package com.chahine.trakt.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    val watchers: Int?,
    val plays: Int?,
    val collectors: Int?,
    val comments: Int?,
    val lists: Int?,
    val votes: Int?,
    @Json(name = "collected_episodes") val collectedEpisodes: Int?
)
