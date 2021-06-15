package com.chahine.showhive.di

import com.chahine.showhive.qualifiers.Tmdb
import com.chahine.tmdb.api.TmdbApi
import com.chahine.tmdb.api.TmdbInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
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
class TmdbApiModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(): TmdbInterceptor {
        return TmdbInterceptor()
    }

    @Provides
    @Singleton
    @Tmdb
    fun provideTmdbHttpUrl() = "https://api.themoviedb.org/3/".toHttpUrl()

    @Provides
    @Singleton
    @Tmdb
    fun provideOkHttpClient(
        client: OkHttpClient,
        interceptor: TmdbInterceptor,
        chuck: ChuckInterceptor
    ): OkHttpClient {
        val clientBuilder = client.newBuilder()

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
            clientBuilder.addInterceptor(chuck)
        }
        clientBuilder.addNetworkInterceptor(interceptor)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    @Tmdb
    fun provideTmdbRetrofit(
        moshi: Moshi,
        @Tmdb baseUrl: HttpUrl,
        @Tmdb client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideTmdbApi(@Tmdb retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }
}
