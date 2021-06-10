package com.chahine.showhive.di

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.chahine.api.DayOfWeekAdapter
import com.chahine.showhive.qualifiers.CacheSize
import com.chahine.showhive.qualifiers.ImageRepo
import com.chahine.trakt.api.ZonedDateTimeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val HEAP_TARGET = 1024 * 1024 / 4
    private const val SHARED_PREFS_FILE = "showhive"
    private const val IMAGE_REPO_PREFS_FILE = "image_repo"

    @Provides
    @CacheSize
    fun getCacheSize(app: Application): Int {
        val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryClass = am.memoryClass
        // Target ~25% of the available heap.
        return HEAP_TARGET * memoryClass
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(ZonedDateTimeConverter())
        .add(DayOfWeekAdapter())
        .build()

    @Provides
    fun provideMasterKey(app: Application): MasterKey {
        return MasterKey(app)
    }

    @Provides
    fun provideSharedPreferences(app: Application, masterKey: MasterKey): SharedPreferences {
        return EncryptedSharedPreferences(app, SHARED_PREFS_FILE, masterKey)
    }

    @Provides
    @ImageRepo
    fun provideImageRepoSharedPreferences(app: Application, masterKey: MasterKey): SharedPreferences {
        return EncryptedSharedPreferences(app, IMAGE_REPO_PREFS_FILE, masterKey)
    }
}
