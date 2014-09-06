package uk.co.grahamcox.ccrpg.webapp.user

/**
 * Data class for the creation of a new user
 */
data class NewUserCommand {
    /** The users email address */
    var email: String? = null
    /** The users screen name */
    var screenName: String? = null
    /** The external provider to use */
    var provider: String? = null
    /** The ID from the external provider  */
    var providerId: String? = null
}
