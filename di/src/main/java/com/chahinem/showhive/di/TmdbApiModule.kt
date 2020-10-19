package com.chahinem.showhive.di

import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.showhive.qualifiers.Tmdb
import com.chahinem.tmdb.api.TmdbApi
import com.chahinem.tmdb.api.TmdbInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class TmdbApiModule {

    @Provides
    @PerApp
    fun provideAuthInterceptor(): TmdbInterceptor {
        return TmdbInterceptor()
    }

    @Provides
    @PerApp
    @Tmdb
    fun provideTmdbHttpUrl() = "https://api.themoviedb.org/3/".toHttpUrl()

    @Provides
    @PerApp
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
    @PerApp
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @PerApp
    fun provideTmdbApi(@Tmdb retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }
}
