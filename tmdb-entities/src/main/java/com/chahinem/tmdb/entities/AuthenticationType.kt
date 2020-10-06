package com.chahinem.tmdb.entities

enum class AuthenticationType(private val value: String) {
    ACCOUNT("account"),
    GUEST("guest");

    override fun toString() = value
}
