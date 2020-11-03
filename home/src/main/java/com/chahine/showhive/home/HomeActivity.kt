package com.chahine.showhive.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.calendar.CalendarFragment
import com.chahine.showhive.home.discover.DiscoverFragment
import com.chahine.showhive.home.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding4.material.itemSelections
import javax.inject.Inject
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

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        navView.setupWithNavController(findNavController(R.id.nav_host_fragment))

//        navView
//            .itemSelections()
//            .take(0) // remove this
//            .distinctUntilChanged()
//            .subscribe({
//                val transaction = supportFragmentManager.beginTransaction()
//                val klass = FRAGMENT_ID_MAP[it.itemId]!!
//                val byTag = supportFragmentManager.findFragmentByTag(klass.simpleName)
//                supportFragmentManager.fragments.forEach { transaction.hide(it) }
//                if (byTag == null) {
//                    transaction.add(R.id.container, klass.newInstance(), klass.simpleName)
//                } else {
//                    transaction.show(byTag)
//                }
//                transaction.commitNowAllowingStateLoss()
//            }, Timber::e)
    }

    companion object {
        private val FRAGMENT_ID_MAP = mapOf(
            R.id.navigation_calendar to CalendarFragment::class.java,
            R.id.navigation_discover to DiscoverFragment::class.java,
            R.id.navigation_profile to ProfileFragment::class.java
        )
    }
}
