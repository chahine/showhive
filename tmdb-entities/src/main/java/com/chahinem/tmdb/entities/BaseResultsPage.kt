package com.chahinem.tmdb.entities

abstract class BaseResultsPage<out T>(
    val id: Int? = null,
    val page: Int? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null,
    val results: List<T>? = null
)
