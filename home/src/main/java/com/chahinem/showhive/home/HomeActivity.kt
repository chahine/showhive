package com.chahinem.showhive.home

import android.os.Bundle
import android.preference.PreferenceManager
import com.chahinem.showhive.base.BaseActivity
import com.chahinem.showhive.base.Router
import com.chahinem.showhive.home.calendar.CalendarFragment
import com.chahinem.showhive.home.discover.DiscoverFragment
import com.chahinem.showhive.home.profile.ProfileFragment
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import kotlinx.android.synthetic.main.activity_home.bottomNavigationView
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity() {

  @Inject lateinit var router: Router

  override fun getLayoutId() = R.layout.activity_home

  lateinit var component: ActivityComponent

  override fun setUpDependencyInjection() {
    component = DaggerActivityComponent.builder()
        .activity(this)
        .activityModule(ActivityModule())
        .appComponent(appComponent)
        .build()
    component.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    if (!prefs.contains("access_token")
        && (!prefs.contains("splash_skipped")
            || !prefs.getBoolean("splash_skipped", false))) {
      router.splash()
    }

    RxBottomNavigationView
        .itemSelections(bottomNavigationView)
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
