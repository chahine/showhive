package com.chahinem.trakt.entities

enum class Extended(private val value: String) {
  FULL("full"),
  NO_SEASONS("noseasons"),
  EPISODES("episodes"),
  FULL_EPISODES("full,episodes");

  override fun toString() = value
}
