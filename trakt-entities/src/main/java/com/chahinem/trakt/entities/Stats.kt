package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Stats(
    val watchers: Int? = null,
    val plays: Int? = null,
    val collectors: Int? = null,
    val comments: Int? = null,
    val lists: Int? = null,
    val votes: Int? = null,
    @Json(name = "collected_episodes") val collectedEpisodes: Int? = null
)
