package com.chahinem.showhive.base

import com.google.firebase.crash.FirebaseCrash
import timber.log.Timber

class FirebaseTree : Timber.Tree() {
  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    FirebaseCrash.log(message)

    t?.let {
      FirebaseCrash.report(it)
    }
  }
}
