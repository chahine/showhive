package com.chahinem.showhive.di

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.chahinem.showhive.qualifiers.PerApp
import dagger.Module
import dagger.Provides

@Module
class ShowHiveModule {

  @Provides
  @PerApp
  fun provideInputMethodService(app: Application): InputMethodManager {
    return app.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  }
}
