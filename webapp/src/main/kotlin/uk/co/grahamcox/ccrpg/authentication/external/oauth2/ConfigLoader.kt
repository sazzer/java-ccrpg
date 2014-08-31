package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import uk.co.grahamcox.ccrpg.authentication.external.oauth2.OAuth2ConfigDao
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import uk.co.grahamcox.ccrpg.dao.BadlyFormedRecordException
import org.slf4j.LoggerFactory
import kotlin.properties.Delegates

/**
 * Mechanism to load the OAuth2 configuration
 */
class ConfigLoader {
    class object {
        private val LOG = LoggerFactory.getLogger(javaClass<ConfigLoader>())
    }
    /** The DAO to load the config with */
    var dao: OAuth2ConfigDao by Delegates.notNull()
    /** The name of the provider to load the config for */
    var provider: String by Delegates.notNull()
    /**
     * Load the configuration
     * @return the configuration
     */
    fun loadConfig() : Config? {
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