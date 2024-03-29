package com.chahine.showhive.di

import com.chahine.showhive.qualifiers.Trakt
import com.chahine.trakt.api.TraktApi
import com.chahine.trakt.api.TraktAuthenticator
import com.chahine.trakt.api.TraktInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TraktApiModule {

    @Provides
    @Singleton
    @Trakt
    fun provideTraktHttpUrl() = "https://api.trakt.tv/".toHttpUrl()

    @Provides
    @Singleton
    @Trakt
    fun provideOkHttpClient(
        client: OkHttpClient,
        interceptor: TraktInterceptor,
        authenticator: TraktAuthenticator,
    ): OkHttpClient {
        val clientBuilder = client.newBuilder()

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.authenticator(authenticator)
        clientBuilder.addNetworkInterceptor(interceptor)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    @Trakt
    fun provideTraktRetrofit(
        moshi: Moshi,
        @Trakt baseUrl: HttpUrl,
        @Trakt client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideTraktApi(@Trakt retrofit: Retrofit): TraktApi {
        return retrofit.create(TraktApi::class.java)
    }
}
