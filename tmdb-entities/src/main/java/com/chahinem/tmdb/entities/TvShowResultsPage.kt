package com.chahinem.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TvShowResultsPage : BaseResultsPage<BaseTvShow>()
