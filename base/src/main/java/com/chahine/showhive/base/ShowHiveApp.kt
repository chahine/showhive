package com.chahine.showhive.base

import com.chahine.showhive.di.DaggerShowHiveComponent
import com.chahine.showhive.di.ShowHiveComponent
import timber.log.Timber

class ShowHiveApp : CoreApp() {
    lateinit var component: ShowHiveComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(FirebaseTree())
        setUpDependencyInjection()
    }

    private fun setUpDependencyInjection() {
        component = DaggerShowHiveComponent.builder()
            .app(this)
            .build()
    }
}
