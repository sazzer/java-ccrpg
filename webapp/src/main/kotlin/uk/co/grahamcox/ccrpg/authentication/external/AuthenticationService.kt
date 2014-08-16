package uk.co.grahamcox.ccrpg.authentication.external

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
     * Get the list of all services that are currently active
     * @return the list of all active services
     */
    fun getActiveServices() : Collection<String> = services.keySet().filter { k -> isActive(k) }
}