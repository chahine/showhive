package com.chahinem.trakt.api

import com.chahinem.api.RxSchedulers
import com.chahinem.trakt.entities.AccessToken
import com.chahinem.trakt.entities.BaseShow
import com.chahinem.trakt.entities.CalendarMovieEntry
import com.chahinem.trakt.entities.CalendarShowEntry
import com.chahinem.trakt.entities.Comment
import com.chahinem.trakt.entities.Credits
import com.chahinem.trakt.entities.Extended
import com.chahinem.trakt.entities.Ratings
import com.chahinem.trakt.entities.Show
import com.chahinem.trakt.entities.Stats
import com.chahinem.trakt.entities.Translation
import com.chahinem.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer

class TraktApiClient(
    private val api: TraktApi,
    private val schedulers: RxSchedulers
) : TraktApi, RxSchedulers {

    override fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return schedulers.applySchedulers()
    }

    // region Authentication

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

    // endregion

    // region Calendars

    override fun myShows(startDate: String, days: Int): Observable<List<CalendarShowEntry>> {
        return api.myShows(startDate, days).compose(applySchedulers())
    }

    override fun myNewShows(startDate: String, days: Int): Observable<List<CalendarShowEntry>> {
        return api.myNewShows(startDate, days).compose(applySchedulers())
    }

    override fun mySeasonPremieres(
        startDate: String,
        days: Int
    ): Observable<List<CalendarShowEntry>> {
        return api.mySeasonPremieres(startDate, days).compose(applySchedulers())
    }

    override fun myMovies(startDate: String, days: Int): Observable<List<CalendarMovieEntry>> {
        return api.myMovies(startDate, days).compose(applySchedulers())
    }

    override fun shows(startDate: String, days: Int): Observable<List<CalendarShowEntry>> {
        return api.shows(startDate, days).compose(applySchedulers())
    }

    override fun newShows(startDate: String, days: Int): Observable<List<CalendarShowEntry>> {
        return api.newShows(startDate, days).compose(applySchedulers())
    }

    override fun seasonPremieres(
        startDate: String,
        days: Int
    ): Observable<List<CalendarShowEntry>> {
        return api.seasonPremieres(startDate, days).compose(applySchedulers())
    }

    override fun movies(startDate: String, days: Int): Observable<List<CalendarMovieEntry>> {
        return api.movies(startDate, days).compose(applySchedulers())
    }

    // endregion

    // region Shows

    override fun popular(page: Int?, limit: Int?, extended: Extended): Observable<List<Show>> {
        return api.popular(page, limit, extended).compose(applySchedulers())
    }

    override fun trending(
        page: Int?,
        limit: Int?,
        extended: Extended
    ): Observable<List<TrendingShow>> {
        return api.trending(page, limit, extended).compose(applySchedulers())
    }

    override fun summary(showId: String, extended: Extended): Observable<Show> {
        return api.summary(showId, extended).compose(applySchedulers())
    }

    override fun translations(showId: String): Observable<List<Translation>> {
        return api.translations(showId).compose(applySchedulers())
    }

    override fun translation(showId: String, language: String): Observable<List<Translation>> {
        return api.translation(showId, language).compose(applySchedulers())
    }

    override fun comments(
        showId: String,
        page: Int?,
        limit: Int?,
        extended: Extended
    ): Observable<List<Comment>> {
        return api.comments(showId, page, limit, extended).compose(applySchedulers())
    }

    override fun collectedProgress(
        showId: String,
        hidden: Boolean?,
        specials: Boolean?,
        extended: Extended
    ): Observable<BaseShow> {
        return api.collectedProgress(showId, hidden, specials, extended).compose(applySchedulers())
    }

    override fun watchedProgress(
        showId: String,
        hidden: Boolean?,
        specials: Boolean?,
        extended: Extended
    ): Observable<BaseShow> {
        return api.watchedProgress(showId, hidden, specials, extended).compose(applySchedulers())
    }

    override fun people(showId: String): Observable<Credits> {
        return api.people(showId).compose(applySchedulers())
    }

    override fun ratings(showId: String): Observable<Ratings> {
        return api.ratings(showId).compose(applySchedulers())
    }

    override fun stats(showId: String): Observable<Stats> {
        return api.stats(showId).compose(applySchedulers())
    }

    override fun related(
        showId: String,
        page: Int?,
        limit: Int?,
        extended: Extended
    ): Observable<List<Show>> {
        return api.related(showId, page, limit, extended).compose(applySchedulers())
    }

    // endregion
}
