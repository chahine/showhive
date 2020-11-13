package com.chahine.showhive.home.discover

import androidx.paging.rxjava3.RxPagingSource
import com.chahine.trakt.api.TraktApiClient
import com.chahine.trakt.entities.Extended
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DiscoverPagingSource @Inject constructor(
    private val traktApiClient: TraktApiClient,
) : RxPagingSource<Int, TrendingShow>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, TrendingShow>> {
        val page = params.key ?: 1

        return traktApiClient.trending(page, params.loadSize, Extended.FULL)
            .map {
                LoadResult.Page(
                    data = it.items,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page == it.pagination.pageCount) null else page + 1
                )
            }
    }
}
