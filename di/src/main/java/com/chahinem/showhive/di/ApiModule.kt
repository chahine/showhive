package com.chahinem.showhive.di

import android.app.Application
import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.showhive.qualifiers.Trakt
import com.chahinem.trakt.api.RxSchedulers
import com.chahinem.trakt.api.TraktApi
import com.chahinem.trakt.api.TraktApiClient
import com.chahinem.trakt.api.TraktAuthInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule(private val app: Application) {

  @Provides
  @PerApp
  fun provideAuthInterceptor(
//      @AccessToken accessToken: StringPreference
  ): TraktAuthInterceptor {
    return TraktAuthInterceptor()
  }

//  @Provides
//  @PerApp
//  fun provideAuthenticator(
//      sessionManager: SessionManager,
//      @Trakt url: HttpUrl,
//      gson: Gson): TraktAuthenticator {
//    return TraktAuthenticator(
//        sessionManager,
//        url,
//        gson
//    )
//  }

  @Provides
  @PerApp
  @Trakt
  fun provideTraktHttpUrl() = HttpUrl.parse("https://api.trakt.tv/")!!

  @Provides
  @PerApp
  @Trakt
  fun provideOkHttpClient(
      client: OkHttpClient,
      authInterceptor: TraktAuthInterceptor,
//      authenticator: TraktAuthenticator,
      chuck: ChuckInterceptor): OkHttpClient {
    val clientBuilder = client.newBuilder()

    if (BuildConfig.DEBUG) {
      val httpLoggingInterceptor = HttpLoggingInterceptor()
      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      clientBuilder.addInterceptor(httpLoggingInterceptor)
      clientBuilder.addInterceptor(chuck)
    }
    clientBuilder.addNetworkInterceptor(authInterceptor)
//    clientBuilder.authenticator(authenticator)
    return clientBuilder.build()
  }

  @Provides
  @PerApp
  @Trakt
  fun provideTraktRetrofit(
      moshi: Moshi,
      @Trakt baseUrl: HttpUrl,
      @Trakt client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides
  @PerApp
  fun provideChuck(): ChuckInterceptor {
    return ChuckInterceptor(app)
  }

  @Provides
  @PerApp
  fun provideTraktApiClient(
      @Trakt retrofit: Retrofit,
      scheduler: RxSchedulers): TraktApi {
    return TraktApiClient(
        retrofit.create(TraktApi::class.java),
        scheduler
    )
  }
}
