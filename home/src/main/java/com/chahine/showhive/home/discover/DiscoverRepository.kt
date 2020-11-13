package com.chahine.showhive.home.discover

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DiscoverRepository @Inject constructor(
    private val pagingSource: DiscoverPagingSource
) {

    companion object {
        private const val PAGE_SIZE = 20
    }

    fun trending(): Observable<PagingData<TrendingShow>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { pagingSource }
        ).observable
    }
}
