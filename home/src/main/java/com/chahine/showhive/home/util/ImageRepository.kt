package com.chahine.showhive.home.util

import com.chahine.tmdb.api.TmdbApi
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val tmdbApi: TmdbApi
) {

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    // TODO: replace with disk storage
    private val cache = HashMap<Int, String>()

    suspend fun image(tvShowId: Int): String {
        if (tvShowId !in cache) {
            val posterPath = tmdbApi.tv(tvShowId).posterPath
            cache[tvShowId] = BASE_URL + posterPath
        }
        return cache[tvShowId]!!
    }
}
