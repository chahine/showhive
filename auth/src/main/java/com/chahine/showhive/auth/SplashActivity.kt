package com.chahine.showhive.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.chahine.showhive.auth.databinding.ActivitySplashBinding
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.trakt.api.TraktApiClient
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject lateinit var router: Router
    @Inject lateinit var apiClient: TraktApiClient

    override fun setUpDependencyInjection() {
        val component = DaggerActivityComponent.builder()
            .activity(this)
            .activityModule(ActivityModule())
            .appComponent(appComponent)
            .build()

        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FIXME: throttle first by 300ms
        binding.connectBtn.setOnClickListener { router.connectWithTrakt() }

        // FIXME: throttle first by 300ms
        binding.skipBtn.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putBoolean("splash_skipped", true)
                .apply()
            router.home()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        intent?.data?.let { data ->
            val uri = Uri.parse(data.toString())
            if (uri.queryParameterNames.contains("code")) {
                val code = uri.getQueryParameter("code")
                // TODO: refactor into viewmodel+interactor+repo
                lifecycleScope.launch {
                    val accessToken = apiClient.exchangeCodeForAccessToken(code!!)
                    PreferenceManager.getDefaultSharedPreferences(applicationContext)
                        .edit()
                        .putString("access_token", accessToken.accessToken)
                        .putString("refresh_token", accessToken.refreshToken)
                        .apply()
                    router.home()
                }
            }
        }
    }
}
