package uk.co.grahamcox.ccrpg.authentication.external

import java.net.URI

/**
 * Authenticator that provides authentication support via a third party service
 */
trait Authenticator {
    /**
     * Check if the authenticator is active or not
     * @return True if the authenticator is active. False if not
     */
    fun isActive() : Boolean
    
    /**
     * Generate a URI to redirect the user to in order to perform authentication
     * @param nonce The nonce for the request
     * @return the URI to redirect the user to
     */
    fun getRedirectUri(nonce: Nonce) : URI?
    /**
     * Handle the callback from authenticating a user
     * @param nonce The nonce for the request
     * @param params The callback parameters
     * @return the details of the authenticated user
     */
    fun handleCallback(nonce: Nonce, params: Map<String, String>) : AuthenticatedUser
}