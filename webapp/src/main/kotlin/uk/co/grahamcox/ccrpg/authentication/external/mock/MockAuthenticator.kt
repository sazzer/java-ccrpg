package uk.co.grahamcox.ccrpg.authentication.external.mock

import uk.co.grahamcox.ccrpg.authentication.external.Authenticator
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.ConfigLoader
import uk.co.grahamcox.ccrpg.authentication.external.Nonce
import java.net.URI
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticatedUser
import uk.co.grahamcox.ccrpg.authentication.external.UnsupportedProviderException
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.Config
import kotlin.properties.Delegates

/**
 * Mock Authenticator for testing purposes
 */
class MockAuthenticator : Authenticator {

    /** the mechanism to load the OAuth2 Config */
    var configLoader: ConfigLoader by Delegates.notNull()

    /** {@inheritDoc} */
    override fun isActive(): Boolean {
        return try {
            loadConfig()
            true
        } catch (e : UnsupportedProviderException) {
            false
        }
    }
    /**
     * Load the OAuth 2 configuration
     */
    private fun loadConfig() : Config =
            configLoader.loadConfig() ?: throw UnsupportedProviderException()

    /** {@inheritDoc} */
    override fun getRedirectUri(nonce: Nonce): URI? {
        return loadConfig().redirectUri
    }

    /** {@inheritDoc} */
    override fun handleCallback(nonce: Nonce, params: Map<String, String>): AuthenticatedUser {
        return AuthenticatedUser(source = "mock", id = "mock")
    }

}