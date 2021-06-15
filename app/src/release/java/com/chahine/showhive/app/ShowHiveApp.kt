package com.chahine.showhive.app

import android.app.Application
import com.chahine.showhive.base.FirebaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ShowHiveApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(FirebaseTree())
    }
}
