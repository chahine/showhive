package com.chahine.showhive.di.repo

import android.content.SharedPreferences
import com.chahine.showhive.qualifiers.ImageRepo
import com.chahine.tmdb.api.TmdbApi
import com.chahine.trakt.api.entities.ShowIds
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(
    private val tmdbApi: TmdbApi,
    @ImageRepo private val prefs: SharedPreferences,
) {

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    init {
        Timber.d("ImageRepository#${hashCode()}")
    }

    suspend fun image(ids: ShowIds): String? {
        val tmdb = ids.tmdb ?: return null
        val key = tmdb.toString()
        if (key !in prefs) {
            tmdbApi.tv(tmdb).posterPath?.let { posterPath ->
                prefs.edit().putString(key, BASE_URL + posterPath).apply()
            }
        }
        return prefs.getString(key, null)
    }
}
