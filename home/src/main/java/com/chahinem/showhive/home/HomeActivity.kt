package com.chahinem.showhive.home

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import com.chahinem.showhive.base.BaseActivity
import com.chahinem.showhive.base.Router
import com.chahinem.showhive.home.calendar.CalendarFragment
import com.chahinem.showhive.home.discover.DiscoverFragment
import com.chahinem.showhive.home.profile.ProfileFragment
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.bottomNavigationView
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject lateinit var router: Router
  @Inject lateinit var injector: DispatchingAndroidInjector<Fragment>

  override fun getLayoutId() = R.layout.activity_home

  override fun setUpDependencyInjection() {
    DaggerActivityComponent.builder()
        .activity(this)
        .appComponent(appComponent)
        .build()
        .inject(this)
  }

  override fun supportFragmentInjector() = injector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    if (!prefs.contains("access_token")
        && (!prefs.contains("splash_skipped")
            || !prefs.getBoolean("splash_skipped", false))) {
      router.splash()
    }

    bottomNavigationView
        .itemSelections()
        .distinctUntilChanged()
        .subscribe({
          val transaction = supportFragmentManager.beginTransaction()
          val klass = FRAGMENT_ID_MAP[it.itemId]!!
          val byTag = supportFragmentManager.findFragmentByTag(klass.simpleName)
          supportFragmentManager.fragments.forEach { transaction.hide(it) }
          if (byTag == null) {
            transaction.add(R.id.container, klass.newInstance(), klass.simpleName)
          } else {
            transaction.show(byTag)
          }
          transaction.commitNowAllowingStateLoss()
        }, Timber::e)
  }

  companion object {
    private val FRAGMENT_ID_MAP = mapOf(
        R.id.action_calendar to CalendarFragment::class.java,
        R.id.action_discover to DiscoverFragment::class.java,
        R.id.action_profile to ProfileFragment::class.java
    )
  }
}
