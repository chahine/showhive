package com.chahine.showhive.home.profile

import com.chahine.trakt.api.entities.User
import java.util.UUID

sealed class ProfileItem(val id: UUID = UUID.randomUUID()) {
    data class UserCard(val user: User) : ProfileItem()
    data class Error(val message: String) : ProfileItem()
    object Loading : ProfileItem()
}
