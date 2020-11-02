package com.chahine.showhive.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager
import com.chahine.showhive.qualifiers.PerApp
import dagger.Module
import dagger.Provides

@Module
class ShowHiveModule {

    @Provides
    @PerApp
    fun provideInputMethodService(app: Application): InputMethodManager {
        return app.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    @Provides
    @PerApp
    fun provideResources(app: Application): Resources {
        return app.resources
    }
}
