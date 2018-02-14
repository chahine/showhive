package com.chahinem.trakt.entities

import com.squareup.moshi.Json

class Crew(
    val writing: List<CrewMember>? = null,
    val production: List<CrewMember>? = null,
    val directing: List<CrewMember>? = null,
    @Json(name = "costume & make-up") var costumeAndMakeUp: List<CrewMember>? = null,
    val art: List<CrewMember>? = null,
    val sound: List<CrewMember>? = null,
    val camera: List<CrewMember>? = null
)
