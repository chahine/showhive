package com.chahine.showhive.home.discover

sealed class DiscoverEvent {
    class LoadTrendingShows(val page: Int = 1) : DiscoverEvent()
}
