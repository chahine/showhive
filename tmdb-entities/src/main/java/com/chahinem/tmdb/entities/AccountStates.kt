package com.chahinem.tmdb.entities

class AccountStates(
    val id: Int,
    val rated: Boolean,
    val rating: RatingObject,
    val favorite: Boolean? = null,
    val episode_number: Int? = null,
    val watchlist: Boolean? = null
)