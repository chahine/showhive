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
import javax.inject.Singleton

@Module
class DataModule {

    companion object {
        private const val HEAP_TARGET = 1024 * 1024 / 4
        private const val SHARED_PREFS_FILE = "showhive"
        private const val IMAGE_REPO_PREFS_FILE = "image_repo"
    }

    @Provides
    @Singleton
    @CacheSize
    fun getCacheSize(app: Application): Int {
        val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryClass = am.memoryClass
        // Target ~25% of the available heap.
        return HEAP_TARGET * memoryClass
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(ZonedDateTimeConverter())
        .add(DayOfWeekAdapter())
        .build()

    @Provides
    @Singleton
    fun provideMasterKey(app: Application): MasterKey {
        return MasterKey.Builder(app)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application, masterKey: MasterKey): SharedPreferences {
        return EncryptedSharedPreferences.create(
            app,
            SHARED_PREFS_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    @ImageRepo
    fun provideImageRepoSharedPreferences(app: Application, masterKey: MasterKey): SharedPreferences {
        return EncryptedSharedPreferences.create(
            app,
            IMAGE_REPO_PREFS_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}
