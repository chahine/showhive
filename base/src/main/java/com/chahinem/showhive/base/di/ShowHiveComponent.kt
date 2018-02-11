package com.chahinem.showhive.base.di

import android.view.inputmethod.InputMethodManager
import com.chahinem.showhive.di.ApiModule
import com.chahinem.showhive.di.DataModule
import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.trakt.api.TraktApi
import com.squareup.picasso.Picasso
import dagger.Component

@PerApp
@Component(modules = [
  ShowHiveModule::class,
  DataModule::class,
  ApiModule::class
])
interface ShowHiveComponent {
  fun inputMethodService(): InputMethodManager

  fun picasso(): Picasso

  fun traktApi(): TraktApi

  @Component.Builder interface Builder {
    fun apiModule(module: ApiModule): Builder
    fun showHiveModule(module: ShowHiveModule): Builder
    fun dataModule(module: DataModule): Builder

    fun build(): ShowHiveComponent
  }
}