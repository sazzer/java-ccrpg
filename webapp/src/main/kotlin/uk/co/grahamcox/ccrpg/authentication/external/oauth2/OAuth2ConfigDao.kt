package uk.co.grahamcox.ccrpg.authentication.external.oauth2

/**
 * Dao for accessing OAuth2 configuration
 */
trait OAuth2ConfigDao {
    /**
     * Try and load the Google Authentication configuration
     * @param provider The name of the provider
     * @return the configuration
     * @throws NoRecordFoundException if the record wasn't found
     */
    fun loadConfig(provider: String): Config;
}