package com.chahine.tmdb.api

import com.chahine.tmdb.api.entities.AccountStates
import com.chahine.tmdb.api.entities.AlternativeTitles
import com.chahine.tmdb.api.entities.AppendToResponse
import com.chahine.tmdb.api.entities.AuthenticationType
import com.chahine.tmdb.api.entities.Changes
import com.chahine.tmdb.api.entities.ContentRatings
import com.chahine.tmdb.api.entities.Credits
import com.chahine.tmdb.api.entities.Images
import com.chahine.tmdb.api.entities.Keywords
import com.chahine.tmdb.api.entities.RatingObject
import com.chahine.tmdb.api.entities.Status
import com.chahine.tmdb.api.entities.TmdbDate
import com.chahine.tmdb.api.entities.Translations
import com.chahine.tmdb.api.entities.TvExternalIds
import com.chahine.tmdb.api.entities.TvShow
import com.chahine.tmdb.api.entities.TvShowResultsPage
import com.chahine.tmdb.api.entities.Videos
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
    suspend fun tv(
        @Path("tv_id") tvShowId: Int,
    ): TvShow

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}")
    suspend fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
    ): TvShow

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("tv/{tv_id}")
    suspend fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: AppendToResponse,
    ): TvShow

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     * @param options *Optional.* parameters for the appended extra results.
     */
    @GET("tv/{tv_id}")
    suspend fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: AppendToResponse,
        @QueryMap options: Map<String, String>,
    ): TvShow

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("tv/{tv_id}")
    suspend fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("append_to_response") appendToResponse: AppendToResponse,
    ): TvShow

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param appendToResponse *Optional.* extra requests to append to the result.
     * @param options *Optional.* parameters for the appended extra results.
     */
    @GET("tv/{tv_id}")
    suspend fun tv(
        @Path("tv_id") tvShowId: Int,
        @Query("append_to_response") appendToResponse: AppendToResponse,
        @QueryMap options: Map<String, String>,
    ): TvShow

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
    suspend fun accountStates(
        @Path("tv_id") tvShowId: Int,
    ): AccountStates

    /**
     * Get the alternative titles for a specific show ID.
     *
     * @param tvShowId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/alternative_titles")
    suspend fun alternativeTitles(
        @Path("tv_id") tvShowId: Int,
    ): AlternativeTitles

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
    suspend fun changes(
        @Path("tv_id") tvShowId: Int,
        @Query("start_date") startDate: TmdbDate,
        @Query("end_date") endDate: TmdbDate,
        @Query("page") page: Int?,
    ): Changes

    /**
     * Get the cast and crew information about a TV series. Just like the website, we pull this information from the
     * last season of the series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/credits")
    suspend fun credits(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
    ): Credits

    /**
     * Get the content ratings for a specific TV show.
     *
     * @param tmdbId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/contentRatings")
    suspend fun contentRatings(
        @Path("tv_id") tmdbId: Int,
    ): ContentRatings

    /**
     * Get the external ids that we have stored for a TV series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/external_ids")
    suspend fun externalIds(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
    ): TvExternalIds

    /**
     * Get the images (posters and backdrops) for a TV series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/images")
    suspend fun images(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
    ): Images

    /**
     * Get the plot keywords for a specific TV show id.
     *
     * @param tvShowId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/keywords")
    suspend fun keywords(
        @Path("tv_id") tvShowId: Int,
    ): Keywords

    /**
     * Get the list of TV show recommendations for this item.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/recommendations")
    suspend fun recommendations(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int?,
        @Query("language") language: String,
    ): TvShowResultsPage

    /**
     * Get the similar TV shows for a specific tv id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/similar")
    suspend fun similar(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int?,
        @Query("language") language: String,
    ): TvShowResultsPage

    /**
     * Get a list of the translations that exist for a TV show.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/translations")
    suspend fun translations(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
    ): Translations

    /**
     * Get the videos that have been added to a TV series (trailers, opening credits, etc...)
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{tv_id}/videos")
    suspend fun videos(
        @Path("tv_id") tvShowId: Int,
        @Query("language") language: String,
    ): Videos

    /**
     * Get the latest TV show id.
     */
    @GET("tv/latest")
    suspend fun latest(): TvShow

    /**
     * Get the list of TV shows that are currently on the air. This query looks for any TV show that has an episode with
     * an air date in the next 7 days.
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/on_the_air")
    suspend fun onTheAir(
        @Query("page") page: Int?,
        @Query("language") language: String,
    ): TvShowResultsPage

    /**
     * Get the list of TV shows that air today. Without a specified timezone, this query defaults to EST (Eastern Time
     * UTC-05:00).
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/airing_today")
    suspend fun airingToday(
        @Query("page") page: Int?,
        @Query("language") language: String,
    ): TvShowResultsPage

    /**
     * Get the list of top rated TV shows. By default, this list will only include TV shows that have 2 or more votes.
     * This list refreshes every day.
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/top_rated")
    suspend fun topRated(
        @Query("page") page: Int?,
        @Query("language") language: String,
    ): TvShowResultsPage

    /**
     * Get the list of popular TV shows. This list refreshes every day.
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/popular")
    suspend fun popular(
        @Query("page") page: Int?,
        @Query("language") language: String,
    ): TvShowResultsPage

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
    suspend fun addRating(
        @Path("tv_id") tvShowId: Int?,
        @Query("authentication") authenticationType: AuthenticationType,
        @Body body: RatingObject,
    ): Status

    /**
     * Rate a TV show.
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     * @param body *Required.* A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("tv/{tv_id}/rating")
    suspend fun addRating(
        @Path("tv_id") tvShowId: Int?,
        @Body body: RatingObject,
    ): Status

    /**
     * Remove your rating for a TV show.
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     * @param authenticationType Authentication Type for this operation. Available Choices: Account, Guest.
     */
    @DELETE("tv/{tv_id}/rating")
    suspend fun deleteRating(
        @Path("tv_id") tvShowId: Int?,
        @Query("authentication") authenticationType: AuthenticationType,
    ): Status

    /**
     * Remove your rating for a TV show.
     *
     * **Requires an active Session.**
     *
     * @param tvShowId TMDb id.
     */
    @DELETE("tv/{tv_id}/rating")
    suspend fun deleteRating(
        @Path("tv_id") tvShowId: Int?,
    ): Status
}
