package com.chahine.tmdb.api

import com.chahine.tmdb.entities.AccountStates
import com.chahine.tmdb.entities.AlternativeTitles
import com.chahine.tmdb.entities.AppendToResponse
import com.chahine.tmdb.entities.AuthenticationType
import com.chahine.tmdb.entities.Changes
import com.chahine.tmdb.entities.ContentRatings
import com.chahine.tmdb.entities.Credits
import com.chahine.tmdb.entities.Images
import com.chahine.tmdb.entities.Keywords
import com.chahine.tmdb.entities.RatingObject
import com.chahine.tmdb.entities.Status
import com.chahine.tmdb.entities.TmdbDate
import com.chahine.tmdb.entities.Translations
import com.chahine.tmdb.entities.TvExternalIds
import com.chahine.tmdb.entities.TvShow
import com.chahine.tmdb.entities.TvShowResultsPage
import com.chahine.tmdb.entities.Videos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

@Suppress("ComplexInterface", "MethodOverloading", "TooManyFunctions")
interface TmdbApi {

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}")
    fun tv(
        @Path("tv_id") tvShowId: Int
    ): Single<TvShow>

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}")
    fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String
    ): Single<TvShow>

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("tv/{tv_id}")
    fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: AppendToResponse
    ): Single<TvShow>

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     * @param options *Optional.* parameters for the appended extra results.
     */
    @GET("tv/{tv_id}")
    fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: AppendToResponse,
        @QueryMap options: Map<String, String>
    ): Single<TvShow>

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("tv/{tv_id}")
    fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("append_to_response") appendToResponse: AppendToResponse
    ): Single<TvShow>

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     * @param options *Optional.* parameters for the appended extra results.
     */
    @GET("tv/{tv_id}")
    fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("append_to_response") appendToResponse: AppendToResponse,
        @QueryMap options: Map<String, String>
    ): Single<TvShow>

    /**
     * Grab the following account states for a session:
     *
     * * TV show rating
     * * If it belongs to your watchlist
     * * If it belongs to your favourite list
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     */
    @GET("tv/{tv_id}/account_states")
    fun accountStates(
        @Path("tv_id") tvShowId: Int
    ): Single<AccountStates>

    /**
     * Get the alternative titles for a specific show ID.
     *
     * @param tvShowId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/alternative_titles")
    fun alternativeTitles(
        @Path("tv_id") tvShowId: Int
    ): Single<AlternativeTitles>

    /**
     * Get the changes for a TV show. By default only the last 24 hours are returned.
     *
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * TV show changes are different than movie changes in that there are some edits on seasons and episodes that will create a change entry at the show level.
     * These can be found under the season and episode keys.
     * These keys will contain a series_id and episode_id.
     * You can use the season changes and episode changes methods to look these up individually.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param start_date *Optional.* Starting date of changes occurred to a movie.
     * @param end_date *Optional.* Ending date of changes occurred to a movie.
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     */
    @GET("tv/{tv_id}/changes")
    fun changes(
        @Path("tv_id") tvShowId: Int,
        @Query("start_date") startDate: TmdbDate,
        @Query("end_date") endDate: TmdbDate,
        @Query("page") page: Int?
    ): Single<Changes>

    /**
     * Get the cast and crew information about a TV series. Just like the website, we pull this information from the
     * last season of the series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/credits")
    fun credits(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String
    ): Single<Credits>

    /**
     * Get the content ratings for a specific TV show.
     *
     * @param tmdbId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/contentRatings")
    fun contentRatings(
        @Path("tv_id") tmdbId: Int
    ): Single<ContentRatings>

    /**
     * Get the external ids that we have stored for a TV series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/external_ids")
    fun externalIds(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String
    ): Single<TvExternalIds>

    /**
     * Get the images (posters and backdrops) for a TV series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/images")
    fun images(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String
    ): Single<Images>

    /**
     * Get the plot keywords for a specific TV show id.
     *
     * @param tvShowId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/keywords")
    fun keywords(
        @Path("tv_id") tvShowId: Int
    ): Single<Keywords>

    /**
     * Get the list of TV show recommendations for this item.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/recommendations")
    fun recommendations(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int?,
        @Query("language") language: String
    ): Single<TvShowResultsPage>

    /**
     * Get the similar TV shows for a specific tv id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/similar")
    fun similar(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int?,
        @Query("language") language: String
    ): Single<TvShowResultsPage>

    /**
     * Get a list of the translations that exist for a TV show.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/translations")
    fun translations(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String
    ): Single<Translations>

    /**
     * Get the videos that have been added to a TV series (trailers, opening credits, etc...)
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/videos")
    fun videos(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String
    ): Single<Videos>

    /**
     * Get the latest TV show id.
     */
    @GET("tv/latest")
    fun latest(): Single<TvShow>

    /**
     * Get the list of TV shows that are currently on the air. This query looks for any TV show that has an episode with
     * an air date in the next 7 days.
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/on_the_air")
    fun onTheAir(
        @Query("page") page: Int?,
        @Query("language") language: String
    ): Single<TvShowResultsPage>

    /**
     * Get the list of TV shows that air today. Without a specified timezone, this query defaults to EST (Eastern Time
     * UTC-05:00).
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/airing_today")
    fun airingToday(
        @Query("page") page: Int?,
        @Query("language") language: String
    ): Single<TvShowResultsPage>

    /**
     * Get the list of top rated TV shows. By default, this list will only include TV shows that have 2 or more votes.
     * This list refreshes every day.
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/top_rated")
    fun topRated(
        @Query("page") page: Int?,
        @Query("language") language: String
    ): Single<TvShowResultsPage>

    /**
     * Get the list of popular TV shows. This list refreshes every day.
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/popular")
    fun popular(
        @Query("page") page: Int?,
        @Query("language") language: String
    ): Single<TvShowResultsPage>

    /**
     * Rate a TV show.
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     * @param authenticationType Authentication Type for this operation. Available Choices: Account, Guest.
     * @param body *Required.* A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("tv/{tv_id}/rating")
    fun addRating(
        @Path("tv_id") tvShowId: Int?,
        @Query("authentication") authenticationType: AuthenticationType,
        @Body body: RatingObject
    ): Single<Status>

    /**
     * Rate a TV show.
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     * @param body *Required.* A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("tv/{tv_id}/rating")
    fun addRating(
        @Path("tv_id") tvShowId: Int?,
        @Body body: RatingObject
    ): Single<Status>

    /**
     * Remove your rating for a TV show.
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     * @param authenticationType Authentication Type for this operation. Available Choices: Account, Guest.
     */
    @DELETE("tv/{tv_id}/rating")
    fun deleteRating(
        @Path("tv_id") tvShowId: Int?,
        @Query("authentication") authenticationType: AuthenticationType
    ): Single<Status>

    /**
     * Remove your rating for a TV show.
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     */
    @DELETE("tv/{tv_id}/rating")
    fun deleteRating(
        @Path("tv_id") tvShowId: Int?
    ): Single<Status>
}
