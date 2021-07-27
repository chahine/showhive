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

    var accessToken: String?
        get() = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        private set(value) = sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, value).apply()

    var refreshToken: String?
        get() = sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
        private set(value) = sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, value).apply()

    val isLoggedIn: Boolean
        get() = !accessToken.isNullOrEmpty()

    fun saveTokens(token: AccessToken) {
        accessToken = token.accessToken
        refreshToken = token.refreshToken
    }

    fun signOut() = with(sharedPreferences.edit()) {
        remove(KEY_ACCESS_TOKEN)
        remove(KEY_REFRESH_TOKEN)
        apply()
    }
}
