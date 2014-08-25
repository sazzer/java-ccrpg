package uk.co.grahamcox.ccrpg.authentication.external.google

import uk.co.grahamcox.ccrpg.authentication.external.Nonce

/**
 * Wrapper around the parameters that are received from Google
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
