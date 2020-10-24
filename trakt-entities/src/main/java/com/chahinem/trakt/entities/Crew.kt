package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Crew(
    val writing: List<CrewMember>?,
    val production: List<CrewMember>?,
    val directing: List<CrewMember>?,
    @Json(name = "costume & make-up") var costumeAndMakeUp: List<CrewMember>?,
    val art: List<CrewMember>?,
    val sound: List<CrewMember>?,
    val camera: List<CrewMember>?
)
