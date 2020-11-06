package com.chahine.showhive.di

import android.app.Application
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager
import com.chahine.tmdb.api.TmdbApi
import com.chahine.trakt.api.TraktApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        DataModule::class,
        ShowHiveModule::class,
        ViewModelModule::class,
        TmdbApiModule::class,
        TraktApiModule::class
    ]
)
interface ShowHiveComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ShowHiveComponent
    }

//    fun inputMethodService(): InputMethodManager
//    fun resources(): Resources
//    fun tmdbApi(): TmdbApi
//    fun traktApi(): TraktApi
}
