package com.chahinem.showhive.base

import android.app.Application
import timber.log.Timber

abstract class CoreApp : Application() {

  override fun onCreate() {
    super.onCreate()

    // FIXME: uncomment when Firebase is implemented
    // Timber.plant(FirebaseTree())
  }
}
