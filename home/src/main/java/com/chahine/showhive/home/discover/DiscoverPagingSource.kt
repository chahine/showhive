package com.chahine.showhive.home.discover

import androidx.paging.rxjava3.RxPagingSource
import com.chahine.trakt.api.TraktApiClient
import com.chahine.trakt.entities.Extended
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DiscoverPagingSource @Inject constructor(
    private val traktApiClient: TraktApiClient,
) : RxPagingSource<Int, TrendingShow>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, TrendingShow>> {
        val position = params.key ?: 1

        return traktApiClient.trending(position, 20, Extended.FULL)
            .subscribeOn(Schedulers.io())
            .map {
                LoadResult.Page(
                    data = it.value,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == it.pagination.pageCount) null else position + 1
                )
            }
    }
}
