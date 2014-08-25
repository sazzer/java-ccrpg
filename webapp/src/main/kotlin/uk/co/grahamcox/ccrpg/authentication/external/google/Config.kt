package uk.co.grahamcox.ccrpg.authentication.external.google

import java.net.URI

/**
 * The configuration for the Google Authenticator to work
 * @param clientId The Client ID to use
 * @param clientSecret The Client Secret to use
 * @param redirectUri The redirect URI to use
 * @param authorizationEndpoint The Google Authorization Endpoint to use
 * @param tokenEndpoint The Google Authorization Endpoint to use
 */
data class Config(val clientId: String,
                  val clientSecret: String,
                  val redirectUri: URI,
                  val authorizationEndpoint: URI,
                  val tokenEndpoint: URI)
