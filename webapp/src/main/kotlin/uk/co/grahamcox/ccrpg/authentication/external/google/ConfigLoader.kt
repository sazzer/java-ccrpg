package uk.co.grahamcox.ccrpg.authentication.external.google

import java.net.URI

/**
 * Loader to load the configuration for the Google Authorization endpoint
 */
class ConfigLoader(private val dao: GoogleConfigDao) {
    /**
     * Load the configuration
     * @return the configuration
     */
    fun loadConfig() : Config? = Config(clientId = "380974699378-8l31mu6eo16gbbhq3ph6i4a6ibg3asrt.apps.googleusercontent.com",
            clientSecret = "7Ubw0_-WDRxVVZE113LfQp8Z",
            redirectUri = URI("http://localhost:8080/api/authentication/external/google/callback"),
            authorizationEndpoint = URI("https://accounts.google.com/o/oauth2/auth"),
            tokenEndpoint = URI("https://accounts.google.com/o/oauth2/token"))
}
