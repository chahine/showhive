package com.chahine.showhive.di

import android.app.Application
import com.chahine.showhive.qualifiers.Trakt
import com.chahine.trakt.api.TraktApi
import com.chahine.trakt.api.TraktAuthenticator
import com.chahine.trakt.api.TraktInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class TraktApiModule {

    @Provides
    fun provideTraktInterceptor(app: Application) = TraktInterceptor(app)

    @Provides
    fun provideTraktAuthenticator(
        app: Application,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): TraktAuthenticator {
        return TraktAuthenticator(app, okHttpClient, moshi)
    }

    @Provides
    @Trakt
    fun provideTraktHttpUrl() = "https://api.trakt.tv/".toHttpUrl()

    @Provides
    @Trakt
    fun provideOkHttpClient(
        client: OkHttpClient,
        interceptor: TraktInterceptor,
        authenticator: TraktAuthenticator,
        chuck: ChuckInterceptor
    ): OkHttpClient {
        val clientBuilder = client.newBuilder()

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
            clientBuilder.addInterceptor(chuck)
        }
        clientBuilder.authenticator(authenticator)
        clientBuilder.addNetworkInterceptor(interceptor)
        return clientBuilder.build()
    }

    @Provides
    @Trakt
    fun provideTraktRetrofit(
        moshi: Moshi,
        @Trakt baseUrl: HttpUrl,
        @Trakt client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideTraktApi(@Trakt retrofit: Retrofit): TraktApi {
        return retrofit.create(TraktApi::class.java)
    }
}
