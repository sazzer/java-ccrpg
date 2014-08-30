package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import uk.co.grahamcox.ccrpg.authentication.external.Nonce

/**
 * Representation of the callback params
 * @param params The parameters to work with
 */
data class CallbackParams(val params: Map<String, String>) {
    /** The nonce that came back from the callback */
    val nonce: Nonce?
        get() {
            val nonce = params.get("state")
            return when (nonce) {
                is String -> Nonce(nonce)
                else -> null
            }
        }

    /** The authorization code that came back from the callback */
    val authorizationCode: String? = params.get("code")
}