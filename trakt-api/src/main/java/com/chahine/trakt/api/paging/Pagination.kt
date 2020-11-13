package com.chahine.trakt.api.paging

import okhttp3.Headers

class Pagination(
    val page: Int,
    val limit: Int,
    val pageCount: Int,
    val itemCount: Int,
) {
    companion object {
        fun fromHeaders(headers: Headers): Pagination {
            return Pagination(
                itemCount = headers["x-pagination-item-count"]!!.toInt(),
                limit = headers["x-pagination-limit"]!!.toInt(),
                page = headers["x-pagination-page"]!!.toInt(),
                pageCount = headers["x-pagination-page-count"]!!.toInt(),
            )
        }
    }
}
