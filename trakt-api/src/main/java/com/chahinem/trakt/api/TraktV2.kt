package com.chahinem.trakt.api

/**
 * trakt API v2 URL.
 */
object TraktV2 {
  const val API_HOST = "api.trakt.tv"
  const val API_URL = "https://$API_HOST/"
  const val API_VERSION = "2"

  const val SITE_URL = "https://trakt.tv"
  const val OAUTH2_AUTHORIZATION_URL = SITE_URL + "/oauth/authorize"
  const val OAUTH2_TOKEN_URL = SITE_URL + "/oauth/token"
  const val REDIRECT_URI = "showhive://auth/oauth2callback"

  const val HEADER_AUTHORIZATION = "Authorization"
  const val HEADER_CONTENT_TYPE = "Content-Type"
  const val CONTENT_TYPE_JSON = "application/json"
  const val HEADER_TRAKT_API_VERSION = "trakt-api-version"
  const val HEADER_TRAKT_API_KEY = "trakt-api-key"
}
