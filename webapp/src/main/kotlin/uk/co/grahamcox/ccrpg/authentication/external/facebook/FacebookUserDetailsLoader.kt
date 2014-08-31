package uk.co.grahamcox.ccrpg.authentication.external.facebook

import uk.co.grahamcox.ccrpg.authentication.external.oauth2.UserDetailsLoader
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser
import org.slf4j.LoggerFactory

/**
 * User Details loader to load the details from a Facebook login
 */
class FacebookUserDetailsLoader : UserDetailsLoader {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<FacebookUserDetailsLoader>())
    }
    /** {@inheritDoc} */
    override fun getUserDetails(accessToken: AccessTokenResponse): AuthenticatedUser {
        return AuthenticatedUser(source = "facebook", id = "facebook")
    }
}