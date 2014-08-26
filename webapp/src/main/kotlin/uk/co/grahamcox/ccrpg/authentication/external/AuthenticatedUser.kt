package uk.co.grahamcox.ccrpg.authentication.external

/**
 * Details of a user that has just authenticated
 * @param source The source of the authentication
 * @param id The ID of the user from this Authentication source
 */
data class AuthenticatedUser(val source: String, val id: String)