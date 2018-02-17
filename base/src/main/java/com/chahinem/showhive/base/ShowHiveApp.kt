package com.chahinem.showhive.base

import com.chahinem.showhive.di.DaggerShowHiveComponent
import com.chahinem.showhive.di.ShowHiveComponent
import com.jakewharton.threetenabp.AndroidThreeTen

class ShowHiveApp : CoreApp() {
  lateinit var component: ShowHiveComponent

  override fun onCreate() {
    super.onCreate()

    AndroidThreeTen.init(this)
    setUpDependencyInjection()
  }

  private fun setUpDependencyInjection() {
    component = DaggerShowHiveComponent.builder()
        .app(this)
        .build()
  }
}
