package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import uk.co.grahamcox.ccrpg.authentication.external.oauth2.OAuth2ConfigDao
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import uk.co.grahamcox.ccrpg.dao.BadlyFormedRecordException
import org.slf4j.LoggerFactory

/**
 * Mechanism to load the OAuth2 configuration
 * @param dao The DAO to load the config with
 */
class ConfigLoader(private val dao: OAuth2ConfigDao) {
    class object {
        private val LOG = LoggerFactory.getLogger(javaClass<ConfigLoader>())
    }
    /**
     * Load the configuration
     * @param provider The name of the provider
     * @return the configuration
     */
    fun loadConfig(provider: String) : Config? {
        val result: Config? =
        try {
            dao.loadConfig(provider)
        } catch (e: NoRecordFoundException) {
            LOG.warn("No configuration found", e)
            null
        } catch (e: BadlyFormedRecordException) {
            LOG.warn("Configuration was badly formed", e)
            null
        }
        return result
    }
}