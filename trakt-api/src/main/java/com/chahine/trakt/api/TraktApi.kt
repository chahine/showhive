package com.chahine.trakt.api

import com.chahine.trakt.entities.AccessToken
import com.chahine.trakt.entities.BaseShow
import com.chahine.trakt.entities.CalendarMovieEntry
import com.chahine.trakt.entities.CalendarShowEntry
import com.chahine.trakt.entities.Comment
import com.chahine.trakt.entities.Credits
import com.chahine.trakt.entities.Extended
import com.chahine.trakt.entities.Ratings
import com.chahine.trakt.entities.Show
import com.chahine.trakt.entities.Stats
import com.chahine.trakt.entities.Translation
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

@Suppress("ComplexInterface", "MethodOverloading", "TooManyFunctions")
interface TraktApi {

    // region Authentication

    @FormUrlEncoded
    @POST(TraktV2.OAUTH2_TOKEN_URL)
    fun exchangeCodeForAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String
    ): Single<AccessToken>

    @FormUrlEncoded
    @POST(TraktV2.OAUTH2_TOKEN_URL)
    fun refreshAccessToken(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String
    ): Single<AccessToken>

    // endregion

    // region Calendars

    /**
     * **OAuth Required**
     *
     * @see .shows
     */
    @GET("calendars/my/shows/{startDate}/{days}")
    fun myShows(
        @Path("startDate") startDate: String,
        @Path("days") days: Int,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<List<CalendarShowEntry>>

    /**
     * **OAuth Required**
     *
     * @see .newShows
     */
    @GET("calendars/my/shows/new/{startDate}/{days}")
    fun myNewShows(
        @Path("startDate") startDate: String,
        @Path("days") days: Int
    ): Single<List<CalendarShowEntry>>

    /**
     * **OAuth Required**
     *
     * @see .seasonPremieres
     */
    @GET("calendars/my/shows/premieres/{startDate}/{days}")
    fun mySeasonPremieres(
        @Path("startDate") startDate: String,
        @Path("days") days: Int
    ): Single<List<CalendarShowEntry>>

    /**
     * **OAuth Required**
     *
     * @see .movies
     */
    @GET("calendars/my/movies/{startDate}/{days}")
    fun myMovies(
        @Path("startDate") startDate: String,
        @Path("days") days: Int
    ): Single<List<CalendarMovieEntry>>

    /**
     * Returns all shows airing during the time period specified.
     *
     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/shows/{startDate}/{days}")
    fun shows(
        @Path("startDate") startDate: String,
        @Path("days") days: Int
    ): Single<List<CalendarShowEntry>>

    /**
     * Returns all new show premieres (season 1, episode 1) airing during the time period specified.
     *
     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/shows/new/{startDate}/{days}")
    fun newShows(
        @Path("startDate") startDate: String,
        @Path("days") days: Int
    ): Single<List<CalendarShowEntry>>

    /**
     * Returns all show premieres (any season, episode 1) airing during the time period specified.
     *
     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/shows/premieres/{startDate}/{days}")
    fun seasonPremieres(
        @Path("startDate") startDate: String,
        @Path("days") days: Int
    ): Single<List<CalendarShowEntry>>

    /**
     * Returns all movies with a release date during the time period specified.
     *
     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/movies/{startDate}/{days}")
    fun movies(
        @Path("startDate") startDate: String,
        @Path("days") days: Int
    ): Single<List<CalendarMovieEntry>>

    // endregion

    // region Shows

    /**
     * Returns the most popular shows. Popularity is calculated using the rating percentage and the number of ratings.
     *
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("shows/popular")
    fun popular(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<List<Show>>

    /**
     * Returns all shows being watched right now. Shows with the most users are returned first.
     *
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("shows/trending")
    fun trending(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<Response<List<TrendingShow>>>

    /**
     * Returns a single shows's details.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}")
    fun summary(
        @Path("id") showId: String,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<Show>

    /**
     * Returns all translations for a show, including language and translated values for title and overview.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}/translations")
    fun translations(
        @Path("id") showId: String
    ): Single<List<Translation>>

    /**
     * Returns a single translation for a show. If the translation does not exist, the returned list will be empty.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param language 2-letter language code (ISO 639-1).
     */
    @GET("shows/{id}/translations/{language}")
    fun translation(
        @Path("id") showId: String,
        @Path("language") language: String
    ): Single<List<Translation>>

    /**
     * Returns all top level comments for a show. Most recent comments returned first.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("shows/{id}/comments")
    fun comments(
        @Path("id") showId: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<List<Comment>>

    /**
     * **OAuth Required**
     *
     *
     * Returns collection progress for show including details on all seasons and episodes. The `next_episode`
     * will be the next episode the user should collect, if there are no upcoming episodes it will be set to `null`.
     *
     *
     * By default, any hidden seasons will be removed from the response and stats. To include these and adjust the
     * completion stats, set the `hidden` flag to `true`.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param hidden Include any hidden seasons.
     * @param specials Include specials as season 0.
     */
    @GET("shows/{id}/progress/collection")
    fun collectedProgress(
        @Path("id") showId: String,
        @Query("hidden") hidden: Boolean?,
        @Query("specials") specials: Boolean?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<BaseShow>

    /**
     * **OAuth Required**
     *
     * Returns watched progress for show including details on all seasons and episodes. The `next_episode` will be
     * the next episode the user should watch, if there are no upcoming episodes it will be set to `null`.
     *
     *
     * By default, any hidden seasons will be removed from the response and stats. To include these and adjust the
     * completion stats, set the `hidden` flag to `true`.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param hidden Include any hidden seasons.
     * @param specials Include specials as season 0.
     */
    @GET("shows/{id}/progress/watched")
    fun watchedProgress(
        @Path("id") showId: String,
        @Query("hidden") hidden: Boolean?,
        @Query("specials") specials: Boolean?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<BaseShow>

    /**
     * Returns all actors, directors, writers, and producers for a show.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}/people")
    fun people(
        @Path("id") showId: String
    ): Single<Credits>

    /**
     * Returns rating (between 0 and 10) and distribution for a show.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}/ratings")
    fun ratings(
        @Path("id") showId: String
    ): Single<Ratings>

    /**
     * Returns lots of show stats.
     */
    @GET("shows/{id}/stats")
    fun stats(
        @Path("id") showId: String
    ): Single<Stats>

    /**
     * Returns related and similar shows.
     */
    @GET("shows/{id}/related")
    fun related(
        @Path("id") showId: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Single<List<Show>>

    // endregion
}
