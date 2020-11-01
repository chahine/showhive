package com.chahine.trakt.api

import android.content.Context
import androidx.preference.PreferenceManager
import com.chahine.trakt.entities.AccessToken
import com.squareup.moshi.Moshi
import java.io.IOException
import okhttp3.Authenticator
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TraktAuthenticator(
    context: Context,
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi
) : Authenticator {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    private var accessToken: String?
        get() {
            return prefs.getString("access_token", null)
        }
        set(value) {
            prefs.edit().putString("access_token", value).apply()
        }

    private var refreshToken: String?
        get() {
            return prefs.getString("refresh_token", null)
        }
        set(value) {
            prefs.edit().putString("refresh_token", value).apply()
        }

    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        return handleAuthenticate(response)
    }

    /**
     * If not doing a trakt [TraktV2.API_URL] request tries to refresh the access token with the refresh token.
     *
     * @param response The response passed to [.authenticate].
     * @param trakt The [TraktV2] instance to get the API key from and to set the updated JSON web token on.
     * @return A request with updated authorization header or null if no auth is possible.
     */
    @Throws(IOException::class)
    private fun handleAuthenticate(response: Response): Request? {
        // not a trakt API endpoint (possibly trakt OAuth or other API), give up.
        // failed 2 times, give up.
        // have no refresh token, give up.
        return when {
            TraktV2.API_HOST != response.request.url.host -> null
            responseCount(response) >= 2 -> null
            refreshToken.isNullOrEmpty() -> null

            else -> {
                // try to refresh the access token with the refresh token
                val refreshResponse = refreshAccessToken()
                if (!refreshResponse.isSuccessful) {
                    return null // failed to retrieve a token, give up.
                }
                // store the new tokens
                accessToken = refreshResponse.body()!!.accessToken
                refreshToken = refreshResponse.body()!!.refreshToken

                // retry request
                response.request.newBuilder()
                    .header(TraktV2.HEADER_AUTHORIZATION, "Bearer " + accessToken)
                    .build()
            }
        }
    }

    private fun refreshAccessToken(): retrofit2.Response<AccessToken> {
        val request: Request = Request.Builder()
            .url(TraktV2.OAUTH2_TOKEN_URL)
            .header(TraktV2.HEADER_CONTENT_TYPE, TraktV2.CONTENT_TYPE_JSON)
            .header(TraktV2.HEADER_TRAKT_API_KEY, BuildConfig.TRAKT_CLIENT_ID)
            .header(TraktV2.HEADER_TRAKT_API_VERSION, TraktV2.API_VERSION)
            .post(
                FormBody.Builder()
                    .add("grant_type", "refresh_token")
                    .add("refresh_token", refreshToken.orEmpty())
                    .add("client_id", BuildConfig.TRAKT_CLIENT_ID)
                    .add("client_secret", BuildConfig.TRAKT_CLIENT_SECRET)
                    .add("redirect_uri", TraktV2.REDIRECT_URI)
                    .build()
            )
            .build()

        val response = okHttpClient.newCall(request).execute()
        return if (response.isSuccessful) {
            retrofit2.Response.success(
                moshi.adapter(AccessToken::class.java).fromJson(response.body?.string().orEmpty())
            )
        } else {
            retrofit2.Response.error(response.code, response.body!!)
        }
    }

    private fun responseCount(response: Response): Int {
        var newResponse = response.priorResponse
        var result = 1
        while (newResponse != null) {
            ++result
            newResponse = newResponse.priorResponse
        }
        return result
    }
}
