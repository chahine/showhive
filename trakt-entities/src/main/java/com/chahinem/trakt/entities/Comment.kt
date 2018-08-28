package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

@JsonClass(generateAdapter = true)
class Comment(
    val id: Int? = null,
    @Json(name = "parent_id") val parentId: Int? = null,
    @Json(name = "created_at") val createdAt: ZonedDateTime? = null,
    val comment: String,
    val spoiler: Boolean? = null,
    val review: Boolean? = null,
    val replies: Int? = null,
    val user: User? = null,
    val movie: Movie,
    val show: Show,
    val episode: Episode
)
