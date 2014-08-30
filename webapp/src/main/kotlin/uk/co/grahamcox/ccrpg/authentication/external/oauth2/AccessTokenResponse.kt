package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import uk.co.grahamcox.ccrpg.authentication.external.jwt.JWT

/**
 * Representation of an Access Token response
 * @param params The parameters to work with
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