package com.chahinem.showhive.base

import com.chahinem.showhive.di.ApiModule
import com.chahinem.showhive.di.DaggerShowHiveComponent
import com.chahinem.showhive.di.DataModule
import com.chahinem.showhive.di.ShowHiveComponent
import com.chahinem.showhive.di.ShowHiveModule
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
        .apiModule(ApiModule(this))
        .dataModule(DataModule(this))
        .showHiveModule(ShowHiveModule(this))
        .build()
  }
}
