package com.chahine.showhive.di

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.chahine.showhive.qualifiers.CacheSize
import com.chahine.showhive.qualifiers.PerApp
import com.chahine.trakt.api.ZonedDateTimeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.migration.DisableInstallInCheck

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    companion object {
        private const val HEAP_TARGET = 1024 * 1024 / 4
    }

//    @Provides
//    @CacheSize
//    fun getCacheSize(app: Application): Int {
//        val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val memoryClass = am.memoryClass
//        // Target ~25% of the available heap.
//        return HEAP_TARGET * memoryClass
//    }
//
//    @Provides
//    fun provideMoshi(): Moshi = Moshi.Builder()
//        .add(ZonedDateTimeConverter())
//        .build()
}
