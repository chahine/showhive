package com.chahinem.trakt.api

import com.chahinem.trakt.entities.AccessToken
import io.reactivex.rxjava3.core.Single
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
}
