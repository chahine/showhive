package com.chahine.tmdb.api.entities

enum class AuthenticationType(private val value: String) {
    ACCOUNT("account"),
    GUEST("guest");

    override fun toString() = value
}
