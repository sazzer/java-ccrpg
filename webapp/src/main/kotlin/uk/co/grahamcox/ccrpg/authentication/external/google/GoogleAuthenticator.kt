package uk.co.grahamcox.ccrpg.authentication.external.google

import uk.co.grahamcox.ccrpg.authentication.external.Authenticator
import uk.co.grahamcox.ccrpg.authentication.external.Nonce
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder
import uk.co.grahamcox.ccrpg.authentication.external.UnsupportedProviderException
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate
import org.springframework.util.LinkedMultiValueMap

/**
 * Authenticator for working with the Google+ Authentication API
 * @param configLoader the mechanism to load the Google+ Authentication Config
 */
class GoogleAuthenticator(val configLoader: ConfigLoader) : Authenticator {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<GoogleAuthenticator>())
    }
    /** the mechanism by which to make HTTP calls */
    var restTemplate: RestTemplate = RestTemplate()
    /** {@inheritDoc} */
    override fun isActive(): Boolean {
        return (configLoader.loadConfig() != null)
    }
    /** {@inheritDoc} */
    override fun getRedirectUri(nonce: Nonce): URI? {
        val config = configLoader.loadConfig()
        var result: URI? = null
        if (config != null) {
            LOG.debug("Generating redirect URI for Nonce {} using Client ID {}",
                    nonce, config.clientId)
            result = UriComponentsBuilder.fromUri(config.authorizationEndpoint)
                    ?.queryParam("client_id", config.clientId)
                    ?.queryParam("response_type", "code")
                    ?.queryParam("scope", "openid email")
                    ?.queryParam("redirect_uri", config.redirectUri.toString())
                    ?.queryParam("state", nonce.value)
                    ?.build()
                    ?.toUri()
        }
        return result
    }
    /**
     * Handle the callback from authenticating a user
     * @param nonce The nonce for the request
     * @param params The callback parameters
     */
    override fun handleCallback(nonce: Nonce, params: Map<String, String>) : Unit {
        val callbackParams = CallbackParams(params)
        LOG.debug("Handling callback for nonce {} with params {}", nonce, callbackParams)

        val config = configLoader.loadConfig()
        if (config != null) {
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
            LOG.debug("JWT: {}", tokenResponse.jwt)
        }
    }
}
