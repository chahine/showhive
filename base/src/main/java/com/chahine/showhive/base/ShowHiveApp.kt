package com.chahine.showhive.base

import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class ShowHiveApp : CoreApp() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(FirebaseTree())
    }
}
