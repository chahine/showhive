package com.chahine.showhive.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    companion object {
        private const val DISK_CACHE_SIZE = 50L * 1024 * 1024 // 50MB
        private const val TIMEOUT = 10L
        private const val OKHTTP_CACHE_DIR = "okHttp"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .cache(Cache(File(context.cacheDir, OKHTTP_CACHE_DIR), DISK_CACHE_SIZE))
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}
