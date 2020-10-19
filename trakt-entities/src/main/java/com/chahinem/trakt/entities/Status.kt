package com.chahinem.trakt.entities

enum class Status(private val value: String) {

    ENDED("ended"),
    RETURNING("returning series"),
    CANCELED("canceled"),
    IN_PRODUCTION("in production");

    override fun toString() = value
}
