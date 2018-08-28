package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

@JsonClass(generateAdapter = true)
class User(
    val username: String? = null,
    @Json(name = "private")
    val isPrivate: Boolean? = null,
    val name: String? = null,
    val vip: Boolean? = null,
    @Json(name = "vip_ep") val vipEp: Boolean? = null,
    val ids: UserIds? = null,
    @Json(name = "joined_at") val joinedAt: ZonedDateTime? = null,
    val location: String? = null,
    val about: String? = null,
    val gender: String? = null,
    val age: Int = 0,
    val images: Images? = null
)
