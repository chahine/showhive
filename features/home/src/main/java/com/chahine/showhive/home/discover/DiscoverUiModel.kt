package com.chahine.showhive.home.discover

import com.chahine.trakt.api.entities.Show
import java.util.UUID

data class DiscoverUiModel(val id: UUID = UUID.randomUUID(), val show: Show, val posterUrl: String?)
