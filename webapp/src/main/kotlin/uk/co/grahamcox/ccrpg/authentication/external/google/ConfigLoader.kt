package uk.co.grahamcox.ccrpg.authentication.external.google

import java.net.URI
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import org.slf4j.LoggerFactory

/**
 * Loader to load the configuration for the Google Authorization endpoint
 */
class ConfigLoader(private val dao: GoogleConfigDao) {
    class object {
        private val LOG = LoggerFactory.getLogger(javaClass<ConfigLoader>())
    }
    /**
     * Load the configuration
     * @return the configuration
     */
    fun loadConfig() : Config? {
        val result: Config? =
        try {
            dao.loadConfig()
        } catch (e: NoRecordFoundException) {
            LOG.warn("No configuration found")
            null
        }
        return result
    }
}
