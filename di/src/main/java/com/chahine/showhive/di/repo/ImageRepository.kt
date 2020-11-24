package com.chahine.showhive.di.repo

import com.chahine.tmdb.api.TmdbApi
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(
    private val tmdbApi: TmdbApi
) {

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    init {
        Timber.d("ImageRepository#${hashCode()}")
    }

    // TODO: replace with disk storage and invalidate strategy
    private val cache = HashMap<Int, String>()

    suspend fun image(tvShowId: Int): String {
        if (tvShowId !in cache) {
            val posterPath = tmdbApi.tv(tvShowId).posterPath
            cache[tvShowId] = BASE_URL + posterPath
        }
        return cache[tvShowId]!!
    }
}
