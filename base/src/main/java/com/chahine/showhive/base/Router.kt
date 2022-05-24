package com.chahine.showhive.base

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.chahine.trakt.api.TraktV2
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class Router @Inject constructor(
    @ActivityContext context: Context,
    private val resources: Resources,
) {

    private val activity = context as AppCompatActivity

    fun connectWithTrakt() {
        openUrl(
            Uri.Builder()
                .scheme("https")
                .authority("trakt.tv")
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
        val intent = getIntentForPath(setOf(resources.getString(R.string.path_home)))
        activity.finishAffinity()
        activity.startActivity(intent)
    }

    fun openUrl(url: String) {
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun tvShow(id: String) {
        activity.startActivity(getIntentForPath(setOf(resources.getString(R.string.path_show), id)))
    }

    fun splash() {
        activity.finishAffinity()
        activity.startActivity(getIntentForPath(setOf(resources.getString(R.string.path_splash))))
    }

    private fun getIntentForPath(
        path: Set<String> = emptySet(),
        queries: Map<String, String> = emptyMap(),
    ): Intent {
        val builder = Uri.EMPTY.buildUpon()
            .scheme(resources.getString(R.string.scheme))
            .authority(resources.getString(R.string.host))

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
