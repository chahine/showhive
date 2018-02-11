package com.chahinem.showhive.base.di

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.chahinem.showhive.qualifiers.PerApp
import dagger.Module
import dagger.Provides

@Module
class ShowHiveModule(val app: Application) {

  @Provides
  @PerApp
  fun provideInputMethodService(): InputMethodManager {
    return app.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  }
}
