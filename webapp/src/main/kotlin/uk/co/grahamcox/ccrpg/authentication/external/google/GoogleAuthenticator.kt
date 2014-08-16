package uk.co.grahamcox.ccrpg.authentication.external.google

import uk.co.grahamcox.ccrpg.authentication.external.Authenticator
import uk.co.grahamcox.ccrpg.authentication.external.Nonce
import java.net.URI

/**
 * Authenticator for working with the Google+ Authentication API
 */
class GoogleAuthenticator : Authenticator {
    /** {@inheritDoc} */
    override fun isActive(): Boolean {
        return true
    }
    /** {@inheritDoc} */
    override fun getRedirectUri(nonce: Nonce): URI {
        return URI("http://www.google.com?q=hello")
    }
}