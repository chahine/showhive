package com.chahinem.showhive.di

import android.app.Application
import android.view.inputmethod.InputMethodManager
import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.tmdb.api.TmdbApi
import com.chahinem.trakt.api.TraktApi
import com.squareup.picasso.Picasso
import dagger.BindsInstance
import dagger.Component

@PerApp
@Component(modules = [
  ApiModule::class,
  DataModule::class,
  ShowHiveModule::class,
  ViewModelModule::class,
  TmdbApiModule::class,
  TraktApiModule::class
])
interface ShowHiveComponent {
  fun inputMethodService(): InputMethodManager

  fun picasso(): Picasso

  fun tmdbApi(): TmdbApi
  fun traktApi(): TraktApi

  @Component.Builder interface Builder {
    fun apiModule(module: ApiModule): Builder
    @BindsInstance fun app(app: Application): Builder
    fun dataModule(module: DataModule): Builder
    fun showHiveModule(module: ShowHiveModule): Builder
    fun tmdbApiModule(module: TmdbApiModule): Builder
    fun traktApiModule(module: TraktApiModule): Builder
    fun build(): ShowHiveComponent
  }
}
