package com.chahinem.showhive.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.chahinem.showhive.base.BaseActivity
import com.chahinem.showhive.base.Router
import com.chahinem.trakt.api.TraktApiClient
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_splash.connectBtn
import kotlinx.android.synthetic.main.activity_splash.skipBtn
import timber.log.Timber

class SplashActivity : BaseActivity() {

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
            .throttleFirst(300, MILLISECONDS)
            .subscribe({ router.connectWithTrakt() }, Timber::e)

        skipBtn
            .clicks()
            .throttleFirst(300, MILLISECONDS)
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
                apiClient
                    .exchangeCodeForAccessToken(code!!)
                    .doOnSuccess {
                        PreferenceManager.getDefaultSharedPreferences(this)
                            .edit()
                            .putString("access_token", it.accessToken)
                            .putString("refresh_token", it.refreshToken)
                            .apply()
                    }
                    .subscribe({ router.home() }, Timber::e)
            }
        }
    }
}
