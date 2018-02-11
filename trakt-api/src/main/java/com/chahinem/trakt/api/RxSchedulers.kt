package com.chahinem.trakt.api

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface RxSchedulers {

  fun <T> applySchedulers(): ObservableTransformer<T, T>

  companion object {
    val DEFAULT: RxSchedulers = object : RxSchedulers {
      override fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
          upstream
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
        }
      }
    }
  }
}
