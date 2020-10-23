package com.chahinem.showhive.base

import com.chahinem.showhive.di.DaggerShowHiveComponent
import com.chahinem.showhive.di.ShowHiveComponent

class ShowHiveApp : CoreApp() {
    lateinit var component: ShowHiveComponent

    override fun onCreate() {
        super.onCreate()

        setUpDependencyInjection()
    }

    private fun setUpDependencyInjection() {
        component = DaggerShowHiveComponent.builder()
            .app(this)
            .build()
    }
}
