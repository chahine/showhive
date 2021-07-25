package com.chahine.trakt.api

import android.content.SharedPreferences
import com.chahine.trakt.api.entities.AccessToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TraktTokenManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
    }

    val accessToken: String?
        get() {
            return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        }

    val refreshToken: String?
        get() {
            return sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
        }

    fun saveTokens(accessToken: AccessToken) = with(sharedPreferences.edit()) {
        putString(KEY_ACCESS_TOKEN, accessToken.accessToken)
        putString(KEY_REFRESH_TOKEN, accessToken.refreshToken)
        apply()
    }

    fun signOut() = with(sharedPreferences.edit()) {
        remove(KEY_ACCESS_TOKEN)
        remove(KEY_REFRESH_TOKEN)
        apply()
    }
}
