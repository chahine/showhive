package com.chahinem.showhive.di

import android.app.Application
import com.chahinem.showhive.qualifiers.PerApp
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import java.io.File
import java.util.concurrent.TimeUnit
import okhttp3.Cache
import okhttp3.OkHttpClient

@Module
class ApiModule {

    companion object {
        private const val DISK_CACHE_SIZE = 50L * 1024 * 1024 // 50MB

        /**
         * Seconds before an IO connection times out.
         */
        private const val TIMEOUT = 10L
        private const val OKHTTP_CACHE_DIR = "okHttp"
    }

    @Provides
    @PerApp
    fun provideOkHttpClient(app: Application): OkHttpClient = OkHttpClient.Builder()
        .cache(Cache(File(app.cacheDir, OKHTTP_CACHE_DIR), DISK_CACHE_SIZE))
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @PerApp
    fun provideChuck(app: Application) = ChuckInterceptor(app)
}
