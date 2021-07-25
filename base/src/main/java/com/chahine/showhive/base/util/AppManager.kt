package com.chahine.showhive.base.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    companion object {
        private const val KEY_SPLASH_SKIPPED = "splash_skipped"
    }

    var hasSkippedSplash: Boolean
        get() = sharedPreferences.getBoolean(KEY_SPLASH_SKIPPED, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_SPLASH_SKIPPED, value).apply()
}
