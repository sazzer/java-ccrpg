package uk.co.grahamcox.ccrpg.authentication.external.google

import uk.co.grahamcox.ccrpg.authentication.external.Authenticator
import uk.co.grahamcox.ccrpg.authentication.external.Nonce
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder
import uk.co.grahamcox.ccrpg.authentication.external.UnsupportedProviderException
import org.slf4j.LoggerFactory

/** The logger to use */
val LOG = LoggerFactory.getLogger(javaClass<GoogleAuthenticator>())

/**
 * Representation of a Client ID
 * @param value The Client ID
 */
data class ClientId(val value: String)
/**
 * Representation of a Client Secret
 * @param value The Client Secret
 */
data class ClientSecret(val value: String)
/**
 * The configuration for the Google Authenticator to work
 * @param clientId The Client ID to use
 * @param clientSecret The Client Secret to use
 * @param redirectUri The redirect URI to use
 * @param authorizationEndpoint The Google Authorization Endpoint to use
 * @param tokenEndpoint The Google Authorization Endpoint to use
 */
data class Config(val clientId: ClientId,
             val clientSecret: ClientSecret,
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
    fun loadConfig() : Config? = Config(clientId = ClientId("380974699378-8l31mu6eo16gbbhq3ph6i4a6ibg3asrt.apps.googleusercontent.com"),
            clientSecret = ClientSecret("7Ubw0_-WDRxVVZE113LfQp8Z"),
            redirectUri = URI("http://localhost:8080/api/authentication/external/google/callback"),
            authorizationEndpoint = URI("https://accounts.google.com/o/oauth2/auth"),
            tokenEndpoint = URI("https://accounts.google.com/o/oauth2/token"))
}
/**
 * Authenticator for working with the Google+ Authentication API
 */
class GoogleAuthenticator(val configLoader: ConfigLoader) : Authenticator {
    /** {@inheritDoc} */
    override fun isActive(): Boolean {
        return (configLoader.loadConfig() != null)
    }
    /** {@inheritDoc} */
    override fun getRedirectUri(nonce: Nonce): URI? {
        val config = configLoader.loadConfig()
        var result: URI? = null
        if (config != null) {
            LOG?.debug("Generating redirect URI for Nonce {} using Client ID {}",
                    nonce, config.clientId)
            result = UriComponentsBuilder.fromUri(config.authorizationEndpoint)
                    ?.queryParam("client_id", config.clientId.value)
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
        LOG?.debug("Handling callback for nonce {} with params {}", nonce, params)
    }
}