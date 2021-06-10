package com.chahine.showhive.di

import android.app.Application
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        private const val DISK_CACHE_SIZE = 50L * 1024 * 1024 // 50MB
        private const val TIMEOUT = 10L
        private const val OKHTTP_CACHE_DIR = "okHttp"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(app: Application): OkHttpClient = OkHttpClient.Builder()
        .cache(Cache(File(app.cacheDir, OKHTTP_CACHE_DIR), DISK_CACHE_SIZE))
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideChuck(app: Application) = ChuckInterceptor(app)
}
