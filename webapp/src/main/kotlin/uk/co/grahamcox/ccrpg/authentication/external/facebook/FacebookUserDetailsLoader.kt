package uk.co.grahamcox.ccrpg.authentication.external.facebook

import uk.co.grahamcox.ccrpg.authentication.external.oauth2.UserDetailsLoader
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser
import org.slf4j.LoggerFactory
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.ConfigLoader
import kotlin.properties.Delegates
import org.springframework.web.client.RestTemplate
import uk.co.grahamcox.ccrpg.TextPlainToMapHttpMessageConverter
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder

/**
 * User Details loader to load the details from a Facebook login
 */
class FacebookUserDetailsLoader : UserDetailsLoader {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<FacebookUserDetailsLoader>())
    }
    /** The Facebook URI to call to get the user profile about an access token */
    var userProfileUrl = URI("https://graph.facebook.com/me")

    /** the mechanism by which to make HTTP calls */
    var restTemplate: RestTemplate = RestTemplate();

    /** {@inheritDoc} */
    override fun getUserDetails(accessToken: AccessTokenResponse): AuthenticatedUser {
        val uri = UriComponentsBuilder.fromUri(userProfileUrl)
                ?.queryParam("access_token", accessToken.accessToken)
                ?.build()
                ?.toUri()
                ?: throw IllegalStateException("No URI built")

        LOG.debug("Requesting debug information for access token using: {}", uri)
        val accessTokenDetails = restTemplate.getForObject(uri,
                javaClass<Map<String, String>>()) ?: throw IllegalStateException("No response returned")
        LOG.debug("Debug information for access token: {}", accessTokenDetails)

        return AuthenticatedUser(source = "facebook",
                id = accessTokenDetails.get("id") ?: throw IllegalStateException("No user ID provided"))
    }
}