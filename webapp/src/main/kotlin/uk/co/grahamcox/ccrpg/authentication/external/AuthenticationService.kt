package uk.co.grahamcox.ccrpg.authentication.external

import java.net.URI

/**
 * Exception to throw when an authentication provider is requested that is not supported
 * @param name The name of the provider
 */
class UnsupportedProviderException(val name: String) : Exception()

/**
 * Service to provide access to external authentication providers
 */
class AuthenticationService(val services: Map<String, Authenticator>) {
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
                    ?: throw UnsupportedProviderException(name)

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
     */
    fun handleCallback(name: String, nonce: Nonce, params: Map<String, String>) : Unit {
        services.get(name)?.handleCallback(nonce, params)
                ?: throw UnsupportedProviderException(name)
    }
}