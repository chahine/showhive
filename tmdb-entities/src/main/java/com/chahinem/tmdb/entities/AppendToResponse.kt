package com.chahinem.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AppendToResponse(val items: Array<AppendToResponseItem>) {

    override fun toString(): String {
        if (items.isNotEmpty()) {
            val sb = StringBuilder()
            for (i in items.indices) {
                sb.append(items[i])

                if (i < items.size - 1) {
                    sb.append(',')
                }
            }

            return sb.toString()
        }

        return ""
    }
}
