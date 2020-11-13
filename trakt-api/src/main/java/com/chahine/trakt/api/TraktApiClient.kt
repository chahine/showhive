package com.chahine.trakt.api

import com.chahine.trakt.api.paging.Paged
import com.chahine.trakt.api.paging.Pagination
import com.chahine.trakt.entities.AccessToken
import com.chahine.trakt.entities.Extended
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import javax.inject.Inject

class TraktApiClient @Inject constructor(private val api: TraktApi) {

    fun exchangeCodeForAccessToken(code: String): Single<AccessToken> {
        return api.exchangeCodeForAccessToken(
            grantType = "authorization_code",
            code = code,
            clientId = BuildConfig.TRAKT_CLIENT_ID,
            clientSecret = BuildConfig.TRAKT_CLIENT_SECRET,
            redirectUri = TraktV2.REDIRECT_URI
        )
    }

    fun trending(page: Int, limit: Int, extended: Extended): Single<Paged<TrendingShow>> {
        return api
            .trending(page, limit, extended)
            .flatMap { response ->
                if (response.isSuccessful)
                    Single.just(
                        Paged(
                            items = response.body()!!,
                            pagination = Pagination.fromHeaders(response.headers())
                        )
                    )
                else {
                    Single.error(HttpException(response))
                }
            }
    }
}
