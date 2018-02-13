package com.chahinem.showhive.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.chahinem.showhive.base.BaseActivity
import com.chahinem.trakt.api.TraktApi
import kotlinx.android.synthetic.main.activity_splash.connectBtn
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : BaseActivity() {

  @Inject lateinit var traktApi: TraktApi

  override fun getLayoutId() = R.layout.activity_splash

  override fun setUpDependencyInjection() {
    val component = DaggerActivityComponent.builder()
        .activityModule(ActivityModule())
        .appComponent(appComponent)
        .build()

    component.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    connectBtn.setOnClickListener {
      openUrl("https://api.trakt.tv/oauth/authorize?response_type=code&client_id=${BuildConfig.TRAKT_CLIENT_ID}&redirect_uri=$redirectUrl")
    }
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    setIntent(intent)
    intent?.data?.let {
      val uri = Uri.parse(it.toString())
      if (uri.queryParameterNames.contains("code")) {
        val code = uri.getQueryParameter("code")

        // TODO: refactor into viewmodel+interactor+repo
        traktApi
            .exchangeCodeForAccessToken(
                "authorization_code",
                code,
                BuildConfig.TRAKT_CLIENT_ID,
                BuildConfig.TRAKT_CLIENT_SECRET,
                redirectUrl)
            .subscribe({
              // TODO: save access and refresh token into sharedPrefs
            }, Timber::e)
      }
    }
  }

  fun openUrl(url: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    startActivity(intent)
  }

  companion object {
    const val redirectUrl = "showhive://auth/oauth2callback"
  }
}
