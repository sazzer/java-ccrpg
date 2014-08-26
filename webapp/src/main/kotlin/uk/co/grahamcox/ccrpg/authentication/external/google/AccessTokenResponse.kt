package uk.co.grahamcox.ccrpg.authentication.external.google

/**
 * Representation of the response from requesting the Google+ Access Token
 */
data class AccessTokenResponse(val params: Map<String, String>) {
    /** The actual access token */
    val accessToken = params.get("access_token")
    /** The type of access token */
    val tokenType = params.get("token_type")
    /** The JSON Web Token for the token */
    val jwt: JWT?
        get() {
            val jwtValue = params.get("id_token")
            return when (jwtValue) {
                is String -> JWT(jwtValue)
                else -> null
            }
        }
}
