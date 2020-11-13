package com.chahine.trakt.api.paging

class Paged<R>(
    val items: List<R>,
    val pagination: Pagination,
)
