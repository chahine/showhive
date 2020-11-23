package com.chahine.trakt.api

import android.content.Context
import androidx.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TraktInterceptor(context: Context) : Interceptor {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    private var accessToken: String? = null
        get() {
            return prefs.getString("access_token", null)
        }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return handleIntercept(
            chain,
            BuildConfig.TRAKT_CLIENT_ID,
            accessToken
        )
    }

    /**
     * If the host matches [TraktV2.API_HOST] adds a header for the current [TraktV2.API_VERSION],
     * [TraktV2.HEADER_TRAKT_API_KEY] with the given api key, [TraktV2.HEADER_CONTENT_TYPE]
     * and if not present an Authorization header using the given access token.
     */
    @Throws(IOException::class)
    private fun handleIntercept(
        chain: Interceptor.Chain,
        apiKey: String,
        accessToken: String?
    ): Response {
        val request = chain.request()
        if (TraktV2.API_HOST != request.url.host) {
            // do not intercept requests for other hosts
            // this allows the interceptor to be used on a shared okhttp client
            return chain.proceed(request)
        }

        val builder = request.newBuilder()

        // set required content type, api key and request specific API version
        builder.header(TraktV2.HEADER_CONTENT_TYPE, TraktV2.CONTENT_TYPE_JSON)
        builder.header(TraktV2.HEADER_TRAKT_API_KEY, apiKey)
        builder.header(TraktV2.HEADER_TRAKT_API_VERSION, TraktV2.API_VERSION)

        // add authorization header
        if (hasNoAuthorizationHeader(request) && accessTokenIsNotEmpty(accessToken)) {
            builder.header(TraktV2.HEADER_AUTHORIZATION, "Bearer " + accessToken)
        }
        return chain.proceed(builder.build())
    }

    private fun hasNoAuthorizationHeader(request: Request): Boolean {
        return request.header(TraktV2.HEADER_AUTHORIZATION) == null
    }

    private fun accessTokenIsNotEmpty(accessToken: String?): Boolean {
        return accessToken?.isNotEmpty() ?: false
    }
}
