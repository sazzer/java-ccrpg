package uk.co.grahamcox.ccrpg.authentication.external.facebook

import uk.co.grahamcox.ccrpg.authentication.external.oauth2.UserDetailsLoader
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser
import org.slf4j.LoggerFactory
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.ConfigLoader
import kotlin.properties.Delegates
import org.springframework.web.client.RestTemplate
import uk.co.grahamcox.ccrpg.TextPlainToMapHttpMessageConverter

/**
 * User Details loader to load the details from a Facebook login
 */
class FacebookUserDetailsLoader : UserDetailsLoader {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<FacebookUserDetailsLoader>())
    }
    /** The mechanism to get the applications access token */
    var appAccessTokenLoader: AppAccessTokenLoader by Delegates.notNull()

    /** {@inheritDoc} */
    override fun getUserDetails(accessToken: AccessTokenResponse): AuthenticatedUser {
        val appAccessToken = appAccessTokenLoader.getAppAccessToken()
        return AuthenticatedUser(source = "facebook", id = appAccessToken)
    }
}