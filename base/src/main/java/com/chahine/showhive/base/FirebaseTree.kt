package com.chahine.showhive.base

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class FirebaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        FirebaseCrashlytics.getInstance().log(message)
    }
}
