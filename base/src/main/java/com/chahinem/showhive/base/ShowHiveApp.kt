package com.chahinem.showhive.base

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.chahinem.showhive.base.di.DaggerShowHiveComponent
import com.chahinem.showhive.base.di.ShowHiveComponent
import com.chahinem.showhive.base.di.ShowHiveModule
import com.chahinem.showhive.di.ApiModule
import com.chahinem.showhive.di.DataModule
import com.jakewharton.threetenabp.AndroidThreeTen

class ShowHiveApp : Application(), ActivityLifecycleCallbacks {

  lateinit var component: ShowHiveComponent

  var activity: Activity? = null

  override fun onCreate() {
    super.onCreate()

    AndroidThreeTen.init(this)
    setUpDependencyInjection()
    registerActivityLifecycleCallbacks(this)
  }

  private fun setUpDependencyInjection() {
    component = DaggerShowHiveComponent.builder()
        .apiModule(ApiModule(this))
        .showHiveModule(ShowHiveModule(this))
        .dataModule(DataModule(this))
        .build()
  }

  override fun onActivityPaused(activity: Activity?) {}

  override fun onActivityResumed(activity: Activity?) {
    this.activity = activity
  }

  override fun onActivityStarted(activity: Activity?) {}

  override fun onActivityDestroyed(activity: Activity?) {}

  override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

  override fun onActivityStopped(activity: Activity?) {}

  override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}
}