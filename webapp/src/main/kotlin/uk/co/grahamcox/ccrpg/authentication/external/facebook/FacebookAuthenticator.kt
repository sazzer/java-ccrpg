package uk.co.grahamcox.ccrpg.authentication.external.google

import uk.co.grahamcox.ccrpg.authentication.external.Authenticator
import uk.co.grahamcox.ccrpg.authentication.external.Nonce
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder
import uk.co.grahamcox.ccrpg.authentication.external.UnsupportedProviderException
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate
import org.springframework.util.LinkedMultiValueMap
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.CallbackParams
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.ConfigLoader
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.Config
import uk.co.grahamcox.ccrpg.authentication.external.jwt.JWT
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.OAuth2Authenticator

/**
 * Authenticator for working with the Facebook Authentication API
 * @param configLoader the mechanism to load the Facebook Authentication Config
 */
class FacebookAuthenticator(configLoader: ConfigLoader) : OAuth2Authenticator(configLoader, "email") {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<FacebookAuthenticator>())
    }
    /**
     * Get the user details for the authenticated access token
     */
    override fun getUserDetails(accessToken: AccessTokenResponse): AuthenticatedUser {
        return AuthenticatedUser(source = "facebook", id = "facebook")
    }
}
