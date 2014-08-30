package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import java.net.URI

/**
 * The configuration for an OAuth2 provider
 * @param clientId The OAuth2 Client ID
 * @param clientSecret The OAuth2 Client Secret
 * @param redirectUri The Redirect URI
 * @param authorizationEndpoint The authorization endpoint
 * @param tokenEndpoint the token endpoint
 */
data class Config(val clientId: String,
                  val clientSecret: String,
                  val redirectUri: URI,
                  val authorizationEndpoint: URI,
                  val tokenEndpoint: URI)