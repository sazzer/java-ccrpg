package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser

/**
 * Mechanism to load the user details for an authentication provider
 */
trait UserDetailsLoader {
    /**
     * Get the user details for the authenticated access token
     * @param accessToken the access token
     * @return the user details
     */
    fun getUserDetails(accessToken: AccessTokenResponse): AuthenticatedUser
}