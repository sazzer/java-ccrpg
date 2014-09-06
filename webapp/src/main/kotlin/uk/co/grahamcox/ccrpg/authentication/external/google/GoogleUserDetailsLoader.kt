package uk.co.grahamcox.ccrpg.authentication.external.google

import uk.co.grahamcox.ccrpg.authentication.external.oauth2.UserDetailsLoader
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser
import uk.co.grahamcox.ccrpg.authentication.external.jwt.JWT
import org.slf4j.LoggerFactory

/**
 * User Details Loader to load the details from the Google API
 */
class GoogleUserDetailsLoader : UserDetailsLoader {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<GoogleUserDetailsLoader>())
    }

    /** {@inheritDoc} */
    override fun getUserDetails(accessToken: AccessTokenResponse): AuthenticatedUser {
        val idToken = accessToken.getParam("id_token")
        val jwt = if (idToken != null) {
            JWT(idToken)
        } else {
            null
        }
        LOG?.debug("JWT: {}", jwt)

        return AuthenticatedUser(source = "google", id = jwt?.subject
                ?: throw IllegalStateException("No User ID was returned"))
    }
}
