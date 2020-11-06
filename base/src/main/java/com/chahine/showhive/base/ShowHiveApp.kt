package com.chahine.showhive.base

import com.chahine.showhive.di.DaggerShowHiveComponent
import com.chahine.showhive.di.ShowHiveComponent
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class ShowHiveApp : CoreApp() {
    val appComponent: ShowHiveComponent by lazy {
        initializeComponent()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(FirebaseTree())
    }

    open fun initializeComponent(): ShowHiveComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerShowHiveComponent.factory().create(this)
    }
}
