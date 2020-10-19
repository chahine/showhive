package com.chahinem.showhive.di

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.chahinem.showhive.qualifiers.CacheSize
import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.trakt.api.ZonedDateTimeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @PerApp
    @CacheSize
    fun getCacheSize(app: Application): Int {
        val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryClass = am.memoryClass
        // Target ~25% of the available heap.
        return 1024 * 1024 * memoryClass / 4
    }

    @Provides
    @PerApp
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(ZonedDateTimeConverter())
        .build()
}
