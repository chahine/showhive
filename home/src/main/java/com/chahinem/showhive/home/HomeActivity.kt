package com.chahinem.showhive.home

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.GridLayoutManager
import com.chahinem.showhive.base.BaseActivity
import com.chahinem.showhive.base.Router
import com.chahinem.tmdb.api.TmdbApi
import com.chahinem.tmdb.entities.Images
import com.chahinem.trakt.api.TraktApi
import com.chahinem.trakt.entities.Extended.NO_SEASONS
import com.chahinem.trakt.entities.Show
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_home.list
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity() {

  @Inject lateinit var router: Router
  @Inject lateinit var traktApi: TraktApi
  @Inject lateinit var tmdbApi: TmdbApi
  @Inject lateinit var adapter: HomeAdapter

  override fun getLayoutId() = R.layout.activity_home

  override fun setUpDependencyInjection() {
    DaggerActivityComponent.builder()
        .activity(this)
        .activityModule(ActivityModule())
        .appComponent(appComponent)
        .build()
        .inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    if (!prefs.contains("access_token")
        && (!prefs.contains("splash_skipped")
            || !prefs.getBoolean("splash_skipped", false))) {
      router.splash()
    }

    list.layoutManager = GridLayoutManager(this, 2)
    list.adapter = adapter
  }

  override fun onResume() {
    super.onResume()

    // TODO: refactor into viewmodel+interactor+repo
    traktApi.popular(1, 10, NO_SEASONS)
        .concatMapIterable { it }
        .filter { it.ids?.tmdb != null }
        .concatMap {
          val tmdbId = it.ids?.tmdb
          when (tmdbId) {
            null -> Observable.empty<Pair<Show, Images>>()
            else -> tmdbApi
                .images(tmdbId, "en")
                .zipWith(Observable.just(it), BiFunction { a: Images, b: Show -> Pair(b, a) })

          }
        }
        .map { (show, image) ->
          ShowItemView.Item(
              show.ids?.trakt,
              image.posters?.firstOrNull()?.filePath.orEmpty())
        }
        .toList()
        .subscribe(adapter::swapData, Timber::e)
  }
}
