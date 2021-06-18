package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CrewMember(
    val job: String?,
    val movie: Movie?,
    val show: Show?,
    val person: Person?
)
