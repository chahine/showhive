package com.chahine.showhive.app

import android.app.Application
import com.chahine.showhive.base.FirebaseTree
import timber.log.Timber

class ShowHiveApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(FirebaseTree())
    }
}

