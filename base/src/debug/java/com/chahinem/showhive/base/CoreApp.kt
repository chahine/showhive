package com.chahinem.showhive.base

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber

abstract class CoreApp : Application() {
  lateinit var refWatcher: RefWatcher

  override fun onCreate() {
    super.onCreate()

    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }

    refWatcher = LeakCanary.install(this)
    Timber.plant(Timber.DebugTree())
  }
}
