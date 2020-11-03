package com.chahine.showhive.home

import android.os.Bundle
import androidx.preference.PreferenceManager
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.calendar.CalendarFragment
import com.chahine.showhive.home.discover.DiscoverFragment
import com.chahine.showhive.home.profile.ProfileFragment
import com.jakewharton.rxbinding4.material.itemSelections
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_home.bottomNavigationView
import timber.log.Timber

class HomeActivity : BaseActivity() {

    @Inject lateinit var router: Router

    lateinit var component: ActivityComponent

    override fun getLayoutId() = R.layout.activity_home

    override fun setUpDependencyInjection() {
        component = DaggerActivityComponent.builder()
            .activity(this)
            .appComponent(appComponent)
            .build()
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.contains("access_token") && !prefs.getBoolean("splash_skipped", false)) {
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