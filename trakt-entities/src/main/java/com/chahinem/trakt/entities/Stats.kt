package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Stats(
    val watchers: Int?,
    val plays: Int?,
    val collectors: Int?,
    val comments: Int?,
    val lists: Int?,
    val votes: Int?,
    @Json(name = "collected_episodes") val collectedEpisodes: Int?
)
