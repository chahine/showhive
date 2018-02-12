package com.chahinem.tmdb.api

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class TmdbInterceptor : Interceptor {
  override fun intercept(chain: Chain): Response {
    val original = chain.request()

    val urlBuilder = original.url()
        .newBuilder()
        .addEncodedQueryParameter("api_key", BuildConfig.TMDB_API_KEY)

    return chain.proceed(original.newBuilder()
        .url(urlBuilder.build())
        .build())
  }
}
