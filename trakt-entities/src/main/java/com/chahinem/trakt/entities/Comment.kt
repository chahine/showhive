package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import org.threeten.bp.OffsetDateTime

class Comment(
    val id: Int? = null,
    @Json(name="parent_id") val parentId: Int? = null,
    @Json(name="created_at") val createdAt: OffsetDateTime? = null,
    val comment: String,
    val spoiler: Boolean? = null,
    val review: Boolean? = null,
    val replies: Int? = null,
    val user: User? = null,
    val movie: Movie,
    val show: Show,
    val episode: Episode
)
