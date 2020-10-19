package com.chahinem.tmdb.entities

enum class VideoType constructor(private val value: String) {

    TRAILER("Trailer"),
    TEASER("Teaser"),
    CLIP("Clip"),
    FEATURETTE("Featurette"),
    OPENING_CREDITS("Opening Credits");

    override fun toString() = value
}
