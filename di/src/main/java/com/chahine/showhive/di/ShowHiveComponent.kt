package com.chahine.showhive.di

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager
import com.chahine.showhive.di.repo.ImageRepository
import com.chahine.tmdb.api.TmdbApi
import com.chahine.trakt.api.TraktApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
//@Component(
//    modules = [
//        ApiModule::class,
//        DataModule::class,
//        ShowHiveModule::class,
//        ViewModelModule::class,
//        TmdbApiModule::class,
//        TraktApiModule::class
//    ]
//)
interface ShowHiveComponent {
    fun inputMethodService(): InputMethodManager
    fun resources(): Resources

    fun imageRepository(): ImageRepository
    fun sharedPreferences(): SharedPreferences

    fun tmdbApi(): TmdbApi
    fun traktApi(): TraktApi

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun app(app: Application): Builder
//
//        fun apiModule(module: ApiModule): Builder
//        fun dataModule(module: DataModule): Builder
//        fun showHiveModule(module: ShowHiveModule): Builder
//        fun tmdbApiModule(module: TmdbApiModule): Builder
//        fun traktApiModule(module: TraktApiModule): Builder
//
//        fun build(): ShowHiveComponent
//    }
}
