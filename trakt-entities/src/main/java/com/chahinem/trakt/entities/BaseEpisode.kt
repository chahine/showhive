package com.chahinem.trakt.entities

import org.threeten.bp.OffsetDateTime

open class BaseEpisode(
    val number: Int? = null,
    val collected_at: OffsetDateTime? = null,
    val plays: Int? = null,
    val last_watched_at: OffsetDateTime? = null,
    val completed: Boolean? = null
)
