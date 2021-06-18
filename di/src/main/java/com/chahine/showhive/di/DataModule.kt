package com.chahine.showhive.di

import android.app.ActivityManager
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.chahine.showhive.qualifiers.CacheSize
import com.chahine.showhive.qualifiers.ImageRepo
import com.chahine.trakt.api.MoshiFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    companion object {
        private const val HEAP_TARGET = 1024 * 1024 / 4
        private const val SHARED_PREFS_FILE = "showhive"
        private const val IMAGE_REPO_PREFS_FILE = "image_repo"
    }

    @Provides
    @Singleton
    @CacheSize
    fun getCacheSize(@ApplicationContext context: Context): Int {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryClass = am.memoryClass
        // Target ~25% of the available heap.
        return HEAP_TARGET * memoryClass
    }

    @Provides
    @Singleton
    fun provideMoshi(factory: MoshiFactory): Moshi = factory.make()

    @Provides
    @Singleton
    fun provideMasterKey(@ApplicationContext context: Context): MasterKey {
        return MasterKey(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context, masterKey: MasterKey): SharedPreferences {
        return EncryptedSharedPreferences(context, SHARED_PREFS_FILE, masterKey)
    }

    @Provides
    @Singleton
    @ImageRepo
    fun provideImageRepoSharedPreferences(
        @ApplicationContext context: Context,
        masterKey: MasterKey,
    ): SharedPreferences {
        return EncryptedSharedPreferences(context, IMAGE_REPO_PREFS_FILE, masterKey)
    }
}
