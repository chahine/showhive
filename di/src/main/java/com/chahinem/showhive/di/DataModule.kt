package com.chahinem.showhive.di

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.chahinem.showhive.qualifiers.CacheSize
import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.trakt.api.ZonedDateTimeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    companion object {
        private const val HEAP_TARGET = 1024 * 1024 / 4
    }

    @Provides
    @PerApp
    @CacheSize
    fun getCacheSize(app: Application): Int {
        val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryClass = am.memoryClass
        // Target ~25% of the available heap.
        return HEAP_TARGET * memoryClass
    }

    @Provides
    @PerApp
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(ZonedDateTimeConverter())
        .build()
}
