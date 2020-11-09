package com.chahine.showhive.home.discover

sealed class DiscoverModel {
    object DiscoverIdle : DiscoverModel()
    class DiscoverSuccess(val items: List<DiscoverAdapter.Item>) : DiscoverModel()
    class DiscoverFailure(val error: Throwable) : DiscoverModel()
}
