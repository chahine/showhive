package com.chahinem.tmdb.api

import com.chahinem.api.RxSchedulers
import com.chahinem.tmdb.entities.AccountStates
import com.chahinem.tmdb.entities.AlternativeTitles
import com.chahinem.tmdb.entities.AppendToResponse
import com.chahinem.tmdb.entities.AuthenticationType
import com.chahinem.tmdb.entities.Changes
import com.chahinem.tmdb.entities.ContentRatings
import com.chahinem.tmdb.entities.Credits
import com.chahinem.tmdb.entities.Images
import com.chahinem.tmdb.entities.Keywords
import com.chahinem.tmdb.entities.RatingObject
import com.chahinem.tmdb.entities.Status
import com.chahinem.tmdb.entities.TmdbDate
import com.chahinem.tmdb.entities.Translations
import com.chahinem.tmdb.entities.TvExternalIds
import com.chahinem.tmdb.entities.TvShow
import com.chahinem.tmdb.entities.TvShowResultsPage
import com.chahinem.tmdb.entities.Videos
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class TmdbApiClient(
    private val api: TmdbApi,
    private val schedulers: RxSchedulers
) : TmdbApi, RxSchedulers {

  override fun <T> applySchedulers(): ObservableTransformer<T, T> {
    return schedulers.applySchedulers()
  }

  override fun tv(tvShowId: Int): Observable<TvShow> {
    return api.tv(tvShowId).compose(applySchedulers())
  }

  override fun tv(tvShowId: Int, language: String): Observable<TvShow> {
    return api.tv(tvShowId, language).compose(applySchedulers())
  }

  override fun tv(tvShowId: Int,
                  language: String,
                  appendToResponse: AppendToResponse): Observable<TvShow> {
    return api.tv(tvShowId, language, appendToResponse).compose(applySchedulers())
  }

  override fun tv(tvShowId: Int,
                  language: String,
                  appendToResponse: AppendToResponse,
                  options: Map<String, String>): Observable<TvShow> {
    return api.tv(tvShowId, appendToResponse, options).compose(applySchedulers())
  }

  override fun tv(tvShowId: Int, appendToResponse: AppendToResponse): Observable<TvShow> {
    return api.tv(tvShowId, appendToResponse).compose(applySchedulers())
  }

  override fun tv(tvShowId: Int,
                  appendToResponse: AppendToResponse,
                  options: Map<String, String>): Observable<TvShow> {
    return api.tv(tvShowId, appendToResponse, options).compose(applySchedulers())
  }

  override fun accountStates(tvShowId: Int): Observable<AccountStates> {
    return api.accountStates(tvShowId).compose(applySchedulers())
  }

  override fun alternativeTitles(tvShowId: Int): Observable<AlternativeTitles> {
    return api.alternativeTitles(tvShowId).compose(applySchedulers())
  }

  override fun changes(tvShowId: Int,
                       start_date: TmdbDate,
                       end_date: TmdbDate,
                       page: Int?): Observable<Changes> {
    return api.changes(tvShowId, start_date, end_date, page).compose(applySchedulers())
  }

  override fun credits(tvShowId: Int, language: String): Observable<Credits> {
    return api.credits(tvShowId, language).compose(applySchedulers())
  }

  override fun contentRatings(tmbdId: Int): Observable<ContentRatings> {
    return api.contentRatings(tmbdId).compose(applySchedulers())
  }

  override fun externalIds(tvShowId: Int, language: String): Observable<TvExternalIds> {
    return api.externalIds(tvShowId, language).compose(applySchedulers())
  }

  override fun images(tvShowId: Int, language: String): Observable<Images> {
    return api.images(tvShowId, language).compose(applySchedulers())
  }

  override fun keywords(tvShowId: Int): Observable<Keywords> {
    return api.keywords(tvShowId).compose(applySchedulers())
  }

  override fun recommendations(tvShowId: Int,
                               page: Int?,
                               language: String): Observable<TvShowResultsPage> {
    return api.recommendations(tvShowId, page, language).compose(applySchedulers())
  }

  override fun similar(tvShowId: Int, page: Int?, language: String): Observable<TvShowResultsPage> {
    return api.similar(tvShowId, page, language).compose(applySchedulers())
  }

  override fun translations(tvShowId: Int, language: String): Observable<Translations> {
    return api.translations(tvShowId, language).compose(applySchedulers())
  }

  override fun videos(tvShowId: Int, language: String): Observable<Videos> {
    return api.videos(tvShowId, language).compose(applySchedulers())
  }

  override fun latest(): Observable<TvShow> {
    return api.latest().compose(applySchedulers())
  }

  override fun onTheAir(page: Int?, language: String): Observable<TvShowResultsPage> {
    return api.onTheAir(page, language).compose(applySchedulers())
  }

  override fun airingToday(page: Int?, language: String): Observable<TvShowResultsPage> {
    return api.airingToday(page, language).compose(applySchedulers())
  }

  override fun topRated(page: Int?, language: String): Observable<TvShowResultsPage> {
    return api.topRated(page, language).compose(applySchedulers())
  }

  override fun popular(page: Int?, language: String): Observable<TvShowResultsPage> {
    return api.popular(page, language).compose(applySchedulers())
  }

  override fun addRating(tvShowId: Int?,
                         authenticationType: AuthenticationType,
                         body: RatingObject): Observable<Status> {
    return api.addRating(tvShowId, authenticationType, body).compose(applySchedulers())
  }

  override fun addRating(tvShowId: Int?, body: RatingObject): Observable<Status> {
    return api.addRating(tvShowId, body).compose(applySchedulers())
  }

  override fun deleteRating(tvShowId: Int?,
                            authenticationType: AuthenticationType): Observable<Status> {
    return api.deleteRating(tvShowId, authenticationType).compose(applySchedulers())
  }

  override fun deleteRating(tvShowId: Int?): Observable<Status> {
    return api.deleteRating(tvShowId).compose(applySchedulers())
  }
}