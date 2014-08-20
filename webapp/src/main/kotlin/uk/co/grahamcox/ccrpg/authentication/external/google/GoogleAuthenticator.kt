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
/**
 * Loader to load the configuration for the Google Authorization endpoint
 */
class ConfigLoader {
    /**
     * Load the configuration
     * @return the configuration
     */
    fun loadConfig() : Config? = Config(clientId = "380974699378-8l31mu6eo16gbbhq3ph6i4a6ibg3asrt.apps.googleusercontent.com",
            clientSecret = "7Ubw0_-WDRxVVZE113LfQp8Z",
            redirectUri = URI("http://localhost:8080/api/authentication/external/google/callback"),
            authorizationEndpoint = URI("https://accounts.google.com/o/oauth2/auth"),
            tokenEndpoint = URI("https://accounts.google.com/o/oauth2/token"))
}
/**
 * Wrapper around the parameters that are received from Google
 */
data class CallbackParams(val params: Map<String, String>) {
    /** The nonce that came back from the callback */
    val nonce: Nonce?
        get() {
            val nonce = params.get("state")
            return when (nonce) {
                is String -> Nonce(nonce)
                else -> null
            }
        }

    /** The authorization code that came back from the callback */
    val authorizationCode: String? = params.get("code")
}

/**
 * Representation of the response from requesting the Google+ Access Token
 */
data class AccessTokenResponse(val params: Map<String, String>) {
    /** The actual access token */
    val accessToken = params.get("access_token")
    /** The type of access token */
    val tokenType = params.get("token_type")
    /** The JSON Web Token for the token */
    val jwt: JWT?
        get() {
            val jwtValue = params.get("id_token")
            return when (jwtValue) {
                is String -> JWT(jwtValue)
                else -> null
            }
        }
}
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
