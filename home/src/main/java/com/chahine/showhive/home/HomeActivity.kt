package com.chahine.showhive.home

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject lateinit var router: Router

    lateinit var component: ActivityComponent

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

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }
}
