package com.chahinem.trakt.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TraktAuthInterceptor : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()

    // Don't modify the Authorization header of requests that already have a Authorization
    // header.
    if (!original.headers().get(HEADER_AUTHORIZATION).isNullOrEmpty()) {
      return chain.proceed(original)
    }

    // TODO: pass logged in user's access token

    return chain.proceed(original.newBuilder()
        .header(HEADER_TRAKT_API_KEY, BuildConfig.TRAKT_CLIENT_ID)
        .header(HEADER_TRAKT_API_VERSION, HEADER_TRAKT_API_VERSION_VALUE)
        .method(original.method(), original.body())
        .build())
  }

  companion object {
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_AUTHORIZATION_VALUE = "Bearer %s"
    const val HEADER_TRAKT_API_KEY = "trakt-api-key"
    const val HEADER_TRAKT_API_VERSION = "trakt-api-version"
    const val HEADER_TRAKT_API_VERSION_VALUE = "2"
  }
}
