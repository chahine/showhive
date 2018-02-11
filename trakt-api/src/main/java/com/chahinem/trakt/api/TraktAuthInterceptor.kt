package com.chahinem.trakt.api

import android.content.SharedPreferences
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TraktAuthInterceptor(private val accessToken: SharedPreferences) : Interceptor {
  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()

    // Don't modify the Authorization header of requests that already have a Authorization
    // header.
    if (!original.headers().get(HEADER_AUTHORIZATION).isNullOrEmpty()) {
      return chain.proceed(original)
    }
//    if (accessToken.isSet && !original.isAccessTokenEndpoint()) {
//      return chain.proceed(original.newBuilder()
//          .header(
//              HEADER_AUTHORIZATION,
//              String.format(HEADER_AUTHORIZATION_VALUE, accessToken.get())
//          )
//          .method(original.method(), original.body())
//          .build())
//    }

    return chain.proceed(original)
  }

  companion object {
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_AUTHORIZATION_VALUE = "Bearer %s"
  }
}