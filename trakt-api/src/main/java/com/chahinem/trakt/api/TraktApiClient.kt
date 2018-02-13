package com.chahinem.trakt.api

import com.chahinem.api.RxSchedulers
import com.chahinem.trakt.entities.AccessToken
import com.chahinem.trakt.entities.Extended
import com.chahinem.trakt.entities.Show
import com.chahinem.trakt.entities.TrendingShow
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class TraktApiClient(
    private val api: TraktApi,
    private val schedulers: RxSchedulers
) : TraktApi, RxSchedulers {

  override fun exchangeCodeForAccessToken(
      grantType: String,
      code: String,
      clientId: String,
      clientSecret: String,
      redirectUri: String
  ): Observable<AccessToken> {
    return api
        .exchangeCodeForAccessToken(grantType, code, clientId, clientSecret, redirectUri)
        .compose(applySchedulers())
  }

  override fun refreshAccessToken(
      grantType: String,
      refreshToken: String,
      clientId: String,
      clientSecret: String,
      redirectUri: String
  ): Observable<AccessToken> {
    return api
        .refreshAccessToken(grantType, refreshToken, clientId, clientSecret, redirectUri)
        .compose(applySchedulers())
  }

  override fun <T> applySchedulers(): ObservableTransformer<T, T> {
    return schedulers.applySchedulers()
  }

  override fun popular(page: Int?, limit: Int?, extended: Extended): Observable<List<Show>> {
    return api.popular(page, limit, extended).compose(applySchedulers())
  }

  override fun trending(page: Int?,
                        limit: Int?,
                        extended: Extended): Observable<List<TrendingShow>> {
    return api.trending(page, limit, extended).compose(applySchedulers())
  }
}
