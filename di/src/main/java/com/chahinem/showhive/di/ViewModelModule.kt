package com.chahinem.showhive.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module internal abstract class ViewModelModule {
  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
