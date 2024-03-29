package com.chahine.trakt.api

import com.chahine.trakt.api.entities.AccessToken
import com.squareup.moshi.Moshi
import okhttp3.Authenticator
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Response.error
import retrofit2.Response.success
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TraktAuthenticator @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi,
    private val tokenManager: TraktTokenManager,
) : Authenticator {

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
        return when {
            // not a trakt API endpoint (possibly trakt OAuth or other API), give up.
            TraktV2.API_HOST != response.request.url.host -> null
            // failed 2 times, give up.
            responseCount(response) >= 2 -> null
            // have no refresh token, give up.
            tokenManager.refreshToken.isNullOrEmpty() -> null

            else -> {
                // try to refresh the access token with the refresh token
                val refreshResponse = refreshAccessToken()
                if (!refreshResponse.isSuccessful) {
                    return null // failed to retrieve a token, give up.
                }
                // store the new tokens
                val accessToken = refreshResponse.body()!!
                tokenManager.saveTokens(accessToken)

                // retry request
                response.request.newBuilder()
                    .header(TraktV2.HEADER_AUTHORIZATION, "Bearer ${tokenManager.accessToken}")
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
                    .add("refresh_token", tokenManager.refreshToken.orEmpty())
                    .add("client_id", BuildConfig.TRAKT_CLIENT_ID)
                    .add("client_secret", BuildConfig.TRAKT_CLIENT_SECRET)
                    .add("redirect_uri", TraktV2.REDIRECT_URI)
                    .build()
            )
            .build()

        val response = okHttpClient.newCall(request).execute()
        return if (response.isSuccessful) {
            success(moshi.adapter(AccessToken::class.java).fromJson(response.body?.string().orEmpty()))
        } else {
            error(response.code, response.body!!)
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
