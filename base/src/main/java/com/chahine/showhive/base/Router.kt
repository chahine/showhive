package com.chahine.showhive.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.chahine.trakt.api.TraktV2
import javax.inject.Inject
import timber.log.Timber

class Router @Inject constructor(private val activity: Activity) {

    private val res = activity.resources

    fun connectWithTrakt() {
        openUrl(
            Uri.Builder()
                .scheme("https")
                .authority("api.trakt.tv")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("client_id", BuildConfig.TRAKT_CLIENT_ID)
                .appendQueryParameter("redirect_uri", TraktV2.REDIRECT_URI)
                .build()
                .toString()
        )
    }

    fun home() {
        val intent = getIntentForPath(setOf(res.getString(R.string.path_home)))
        activity.finishAffinity()
        activity.startActivity(intent)
    }

    fun openUrl(url: String) {
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun tvShow(id: String) {
        activity.startActivity(getIntentForPath(setOf(res.getString(R.string.path_show), id)))
    }

    fun splash() {
        activity.finishAffinity()
        activity.startActivity(getIntentForPath(setOf(res.getString(R.string.path_splash))))
    }

    private fun getIntentForPath(
        path: Set<String> = emptySet(),
        queries: Map<String, String> = emptyMap()
    ): Intent {
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
}
