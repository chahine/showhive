package com.chahine.showhive.home.discover

sealed class DiscoverEvent {
    class LoadTrendingShows(val query: String) : DiscoverEvent()
}
