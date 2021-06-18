package com.chahine.trakt.api

import com.chahine.trakt.api.entities.AccessToken
import com.chahine.trakt.api.entities.Extended
import com.chahine.trakt.api.entities.TrendingShow
import com.chahine.trakt.api.paging.Paged
import com.chahine.trakt.api.paging.Pagination
import retrofit2.HttpException
import javax.inject.Inject

class TraktApiClient @Inject constructor(private val api: TraktApi) {

    suspend fun exchangeCodeForAccessToken(code: String): AccessToken {
        return api.exchangeCodeForAccessToken(
            grantType = "authorization_code",
            code = code,
            clientId = BuildConfig.TRAKT_CLIENT_ID,
            clientSecret = BuildConfig.TRAKT_CLIENT_SECRET,
            redirectUri = TraktV2.REDIRECT_URI
        )
    }

    suspend fun trending(page: Int, limit: Int, extended: Extended): Paged<TrendingShow> {
        val response = api.trending(page, limit, extended)
        if (response.isSuccessful) {
            return Paged(
                items = response.body()!!,
                pagination = Pagination.fromHeaders(response.headers())
            )
        } else {
            throw HttpException(response)
        }
    }
}
