package com.chahinem.trakt.api

import com.chahinem.trakt.entities.AccessToken
import com.chahinem.trakt.entities.Extended
import com.chahinem.trakt.entities.Show
import com.chahinem.trakt.entities.TrendingShow
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TraktApi {

  @FormUrlEncoded
  @POST("https://trakt.tv/oauth/token")
  fun exchangeCodeForAccessToken(
      @Field("grant_type") grantType: String,
      @Field("code") code: String,
      @Field("client_id") clientId: String,
      @Field("client_secret") clientSecret: String,
      @Field("redirect_uri") redirectUri: String
  ): Observable<AccessToken>

  @FormUrlEncoded
  @POST("https://trakt.tv/oauth/token")
  fun refreshAccessToken(
      @Field("grant_type") grantType: String,
      @Field("refresh_token") refreshToken: String,
      @Field("client_id") clientId: String,
      @Field("client_secret") clientSecret: String,
      @Field("redirect_uri") redirectUri: String
  ): Observable<AccessToken>

  /**
   * Returns the most popular shows.
   * Popularity is calculated using the rating percentage and the number of ratings.
   *
   * @param page Number of page of results to be returned.
   * @param limit Number of results to return per page.
   */
  @GET("shows/popular")
  fun popular(
      @Query("page") page: Int?,
      @Query("limit") limit: Int?,
      @Query(value = "extended", encoded = true) extended: Extended
  ): Observable<List<Show>>

  /**
   * Returns all shows being watched right now.
   * Shows with the most users are returned first.
   *
   * @param page Number of page of results to be returned.
   * @param limit Number of results to return per page.
   */
  @GET("shows/trending")
  fun trending(
      @Query("page") page: Int?,
      @Query("limit") limit: Int?,
      @Query(value = "extended", encoded = true) extended: Extended
  ): Observable<List<TrendingShow>>
}
