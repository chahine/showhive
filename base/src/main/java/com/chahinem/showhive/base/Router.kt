package com.chahinem.showhive.base

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class Router @Inject constructor(private val activity: AppCompatActivity) {

  private val res = activity.resources

  fun connectWithTrakt() {
    openUrl(Uri.Builder()
        .scheme("https")
        .authority("api.trakt.tv")
        .appendPath("oauth")
        .appendPath("authorize")
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("client_id", BuildConfig.TRAKT_CLIENT_ID)
        .appendQueryParameter("redirect_uri", REDIRECT_URL)
        .build()
        .toString())
  }

  fun home() {
    val intent = getIntentForPath(setOf(res.getString(R.string.path_home)))
    activity.finishAffinity()
    activity.startActivity(intent)
  }

  fun openUrl(url: String) {
    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
  }

  fun splash() {
    val intent = getIntentForPath(setOf(res.getString(R.string.path_splash)))
    activity.startActivity(intent)
  }

  private fun getIntentForPath(
      path: Set<String> = emptySet(),
      queries: Map<String, String> = emptyMap()): Intent {
    val builder = Uri.EMPTY.buildUpon()
        .scheme(res.getString(R.string.scheme))
        .authority(res.getString(R.string.host))

    // need to strip off the leading / needed for Manifest.xml for first path segment
    path.forEach { builder.appendPath(it.removePrefix("/")) }
    queries.forEach { builder.appendQueryParameter(it.key, it.value) }

    val url = builder.build()
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url.toString())
    )
    intent.`package` = activity.packageName
    intent.addCategory(Intent.CATEGORY_BROWSABLE)
    Timber.d("intentForPath=$url")
    return intent
  }

  companion object {
    const val REDIRECT_URL = "showhive://auth/oauth2callback"
  }
}
