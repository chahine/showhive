package com.chahine.fanart.api

import com.chahine.fanart.api.entities.Show
import retrofit2.http.GET
import retrofit2.http.Path

interface FanartApi {

    /**
     * @param id tvdbId
     * Get image for show
     */
    @GET("tv/{id}")
    suspend fun show(@Path("id") id: String): List<Show>
}
