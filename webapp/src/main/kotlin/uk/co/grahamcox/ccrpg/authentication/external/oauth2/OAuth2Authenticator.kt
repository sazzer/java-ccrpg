package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import uk.co.grahamcox.ccrpg.authentication.external.Authenticator
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate
import uk.co.grahamcox.ccrpg.authentication.external.UnsupportedProviderException
import uk.co.grahamcox.ccrpg.authentication.external.Nonce
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser
import org.springframework.util.LinkedMultiValueMap
import uk.co.grahamcox.ccrpg.TextPlainToMapHttpMessageConverter

/**
 * Authenticator for working with OAuth2 Authentication APIs
 * @param configLoader the mechanism to load the OAuth2 Config
 */
abstract class OAuth2Authenticator(private val configLoader: ConfigLoader, private val scopes: String) : Authenticator {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<OAuth2Authenticator>())
    }
    /** the mechanism by which to make HTTP calls */
    var restTemplate: RestTemplate = RestTemplate();

    {
        restTemplate.getMessageConverters()?.add(TextPlainToMapHttpMessageConverter())
    }

    /** {@inheritDoc} */
    override fun isActive(): Boolean {
        return try {
            loadConfig()
            true
        } catch (e : UnsupportedProviderException) {
            false
        }
    }
    /**
     * Load the OAuth 2 configuration
     */
    private fun loadConfig() : Config =
            configLoader.loadConfig() ?: throw UnsupportedProviderException()

    /** {@inheritDoc} */
    override fun getRedirectUri(nonce: Nonce): URI? {
        val config = loadConfig()

        LOG.debug("Generating redirect URI for Nonce {} using Client ID {}",
                nonce, config.clientId)
        val result = UriComponentsBuilder.fromUri(config.authorizationEndpoint)
                ?.queryParam("client_id", config.clientId)
                ?.queryParam("response_type", "code")
                ?.queryParam("scope", scopes)
                ?.queryParam("redirect_uri", config.redirectUri.toString())
                ?.queryParam("state", nonce.value)
                ?.build()
                ?.toUri()
        return result
    }
    /**
     * Handle the callback from authenticating a user
     * @param nonce The nonce for the request
     * @param params The callback parameters
     * @return the details of the authenticated user
     */
    override fun handleCallback(nonce: Nonce, params: Map<String, String>) : AuthenticatedUser {
        val callbackParams = CallbackParams(params)
        LOG.debug("Handling callback for nonce {} with params {}", nonce, callbackParams)

        val config = loadConfig()
        val tokenRequestParams = LinkedMultiValueMap<String, String?>()
        tokenRequestParams.add("code", callbackParams.authorizationCode)
        tokenRequestParams.add("client_id", config.clientId)
        tokenRequestParams.add("client_secret", config.clientSecret)
        tokenRequestParams.add("redirect_uri", config.redirectUri.toString())
        tokenRequestParams.add("grant_type", "authorization_code")

        val tokenResponse = AccessTokenResponse(restTemplate.postForObject(config.tokenEndpoint,
                tokenRequestParams,
                javaClass<Map<String, String>>()) ?: throw IllegalArgumentException("No response returned"))

        LOG.debug("Response from requesting the authorization token: {}", tokenResponse)
        return getUserDetails(tokenResponse)
    }
    /**
     * Get the user details for the authenticated access token
     */
    abstract fun getUserDetails(accessToken: AccessTokenResponse): AuthenticatedUser
}
