package com.chahine.showhive.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ShowHiveModule {

    @Provides
    fun provideInputMethodService(app: Application): InputMethodManager {
        return app.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    @Provides
    fun provideResources(app: Application): Resources {
        return app.resources
    }
}
