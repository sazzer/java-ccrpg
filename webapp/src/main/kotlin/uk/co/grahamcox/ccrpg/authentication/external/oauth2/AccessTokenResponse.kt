package uk.co.grahamcox.ccrpg.authentication.external.oauth2

/**
 * Representation of an Access Token response
 * @param params The parameters to work with
 */
data class AccessTokenResponse(val params: Map<String, String>) {
    /** The actual access token */
    val accessToken = params.get("access_token")
    /** The type of access token */
    val tokenType = params.get("token_type")

    /**
     * Get the Access Token parameter with the given key
     * @param key The key to get
     * @return the parameter
     */
    fun getParam(key: String): String? = params.get(key)
}
