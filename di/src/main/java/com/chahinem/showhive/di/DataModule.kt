package com.chahinem.showhive.di

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.chahinem.showhive.qualifiers.CacheSize
import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.trakt.api.RxSchedulers
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class DataModule(private val app: Application) {
  companion object {
    private const val DISK_CACHE_SIZE = 50L * 1024 * 1024 // 50MB

    /**
     * Seconds before an IO connection times out.
     */
    private const val TIMEOUT = 10L
    private const val PICASSO_CACHE_DIR = "picasso"
    private const val OKHTTP_CACHE_DIR = "okHttp"
  }

  // This client and cache are shared by Retrofit and Picasso
  private fun createOkHttpClient() = OkHttpClient.Builder()
      .cache(Cache(File(app.cacheDir, OKHTTP_CACHE_DIR), DISK_CACHE_SIZE))
      .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
      .readTimeout(TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
      .build()

  @Provides
  @PerApp
  @CacheSize
  fun getCacheSize(): Int {
    val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val memoryClass = am.memoryClass
    // Target ~25% of the available heap.
    return 1024 * 1024 * memoryClass / 4
  }

  @Provides
  @PerApp
  fun provideMoshi(): Moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()

  @Provides
  @PerApp
  fun provideOkHttpClient(): OkHttpClient = createOkHttpClient()

  @Provides
  @PerApp
  fun provideScheduler() = RxSchedulers.DEFAULT

  @Provides
  @PerApp
  fun providePicasso(client: OkHttpClient, @CacheSize cacheSize: Int): Picasso = Picasso.Builder(app)
      .downloader(OkHttp3Downloader(client
          .newBuilder()
          .cache(Cache(File(app.cacheDir, PICASSO_CACHE_DIR), DISK_CACHE_SIZE))
          .build()))
      .memoryCache(LruCache(cacheSize))
      .listener { _, uri, e -> Timber.e(e, "Failed to load image: %s", uri) }
      .build()
}
