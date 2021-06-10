package com.chahine.showhive.home

import android.content.SharedPreferences
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    @Inject lateinit var router: Router
    @Inject lateinit var sharedPreferences: SharedPreferences
//    lateinit var component: ActivityComponent

    override fun setUpDependencyInjection() {
//        component = DaggerActivityComponent.builder()
//            .activity(this)
//            .appComponent(appComponent)
//            .build()
//        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!sharedPreferences.contains("access_token") && !sharedPreferences.getBoolean("splash_skipped", false)) {
            router.splash()
        }

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }
}
