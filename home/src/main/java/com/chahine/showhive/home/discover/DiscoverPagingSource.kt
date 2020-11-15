package com.chahine.showhive.home.discover

import androidx.paging.PagingSource
import com.chahine.trakt.api.TraktApiClient
import com.chahine.trakt.entities.Extended
import com.chahine.trakt.entities.TrendingShow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DiscoverPagingSource @Inject constructor(
    private val traktApiClient: TraktApiClient,
) : PagingSource<Int, TrendingShow>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingShow> {
        val page = params.key ?: 1

        return try {
            val response = traktApiClient.trending(page, params.loadSize, Extended.FULL)
            LoadResult.Page(
                data = response.items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.pagination.pageCount) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
