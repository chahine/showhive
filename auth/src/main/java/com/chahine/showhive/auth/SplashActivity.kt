package com.chahine.showhive.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.trakt.api.TraktApiClient
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.activity_splash.connectBtn
import kotlinx.android.synthetic.main.activity_splash.skipBtn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    companion object {
        private const val WINDOW_DURATION = 300L
    }

    @Inject lateinit var router: Router
    @Inject lateinit var apiClient: TraktApiClient

    override fun getLayoutId() = R.layout.activity_splash

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

        // FIXME: dispose of these
        connectBtn
            .clicks()
            .throttleFirst(WINDOW_DURATION, TimeUnit.MILLISECONDS)
            .subscribe({ router.connectWithTrakt() }, Timber::e)

        skipBtn
            .clicks()
            .throttleFirst(WINDOW_DURATION, TimeUnit.MILLISECONDS)
            .doOnNext {
                PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putBoolean("splash_skipped", true)
                    .apply()
            }
            .subscribe({ router.home() }, Timber::e)
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
