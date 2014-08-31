package uk.co.grahamcox.ccrpg.authentication.external

import java.net.URI
import kotlin.properties.Delegates

/**
 * Service to provide access to external authentication providers
 */
class AuthenticationService {
    /** The providers to work with */
    var services: Map<String, Authenticator> by Delegates.notNull()
    /**
     * Check if the requested Authentication Service is available and active
     * @param name The name of the service
     * @return true if the service is registered and active. False if not
     */
    fun isActive(name: String): Boolean = services.get(name)?.isActive() ?: false

    /**
     * Generate a URI to redirect the user to in order to perform authentication
     * @param name The name of the service
     * @param nonce The nonce for the request
     * @return the URI to redirect the user to
     */
    fun getRedirectUri(name: String, nonce: Nonce) : URI =
            services.get(name)?.getRedirectUri(nonce)
                    ?: throw UnsupportedProviderException()

    /**
     * Get the list of all services that are currently active
     * @return the list of all active services
     */
    fun getActiveServices() : Collection<String> = services.keySet().filter { k -> isActive(k) }
    /**
     * Handle the callback from authenticating a user
     * @param name The name of the service
     * @param nonce The nonce for the request
     * @param params The callback parameters
     * @return the details of the authenticated user
     */
    fun handleCallback(name: String, nonce: Nonce, params: Map<String, String>) : AuthenticatedUser =
        services.get(name)?.handleCallback(nonce, params)
                ?: throw UnsupportedProviderException()
}