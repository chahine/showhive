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
        private const val TMDB_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    init {
        Timber.d("ImageRepository#${hashCode()}")
    }

    suspend fun image(ids: ShowIds): String? {
        val key = ids.slug ?: return null
        if (key !in prefs) {
            val tmdb = ids.tmdb
            val imdb = ids.imdb
            if (tmdb != null) {
                tmdbApi.tv(tmdb).posterPath?.let { posterPath ->
                    prefs.edit().putString(key, TMDB_BASE_URL + posterPath).apply()
                }
            } else if (imdb != null) {

            }
        }
        return prefs.getString(key, null)
    }
}
