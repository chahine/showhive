package com.chahine.showhive.base

import android.app.Application
import timber.log.Timber

abstract class CoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(FirebaseTree())
    }
}
