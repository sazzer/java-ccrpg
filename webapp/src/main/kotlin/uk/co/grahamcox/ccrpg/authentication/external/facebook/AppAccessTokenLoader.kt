package uk.co.grahamcox.ccrpg.authentication.external.facebook

import uk.co.grahamcox.ccrpg.authentication.external.oauth2.ConfigLoader
import kotlin.properties.Delegates
import org.springframework.web.client.RestTemplate
import uk.co.grahamcox.ccrpg.TextPlainToMapHttpMessageConverter
import org.springframework.util.LinkedMultiValueMap
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import org.springframework.web.util.UriComponentsBuilder
import org.slf4j.LoggerFactory

/**
 * Mechanism to retrieve the application access token
 */
class AppAccessTokenLoader {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<AppAccessTokenLoader>())
    }
    /** the mechanism to load the OAuth2 Config */
    var configLoader: ConfigLoader by Delegates.notNull()
    /** the mechanism by which to make HTTP calls */
    var restTemplate: RestTemplate = RestTemplate();

    {
        restTemplate.getMessageConverters()?.add(TextPlainToMapHttpMessageConverter())
    }

    /**
     * Get the applications access token
     * @return the access token
     */
    fun getAppAccessToken(): String {
        val config = configLoader.loadConfig()

        val uri = UriComponentsBuilder.fromUri(config?.tokenEndpoint)
            ?.queryParam("client_id", config?.clientId)
            ?.queryParam("client_secret", config?.clientSecret)
            ?.queryParam("grant_type", "client_credentials")
            ?.build()
            ?.toUri()
            ?: throw IllegalStateException("No URI built")

        LOG.debug("Requesting application access token using: {}", uri)

        val appTokenResponse = restTemplate.getForObject(uri,
                javaClass<Map<String, String>>()) ?: throw IllegalStateException("No response returned")

        return appTokenResponse.get("access_token") ?: throw IllegalStateException("No access token returned")
    }
}