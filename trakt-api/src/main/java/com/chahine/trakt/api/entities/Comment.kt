package com.chahine.trakt.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
data class Comment(
    val id: Int?,
    @Json(name = "parent_id") val parentId: Int?,
    @Json(name = "created_at") val createdAt: ZonedDateTime?,
    val comment: String,
    val spoiler: Boolean?,
    val review: Boolean?,
    val replies: Int?,
    val user: User?,
    val movie: Movie,
    val show: Show,
    val episode: Episode
)
