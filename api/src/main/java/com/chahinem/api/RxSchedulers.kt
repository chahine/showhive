package com.chahinem.api

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

interface RxSchedulers {

    fun <T> applySchedulers(): ObservableTransformer<T, T>

    companion object {
        val DEFAULT: RxSchedulers = object : RxSchedulers {
            override fun <T> applySchedulers(): ObservableTransformer<T, T> =
                ObservableTransformer { upstream ->
                    upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                }
        }
    }
}
