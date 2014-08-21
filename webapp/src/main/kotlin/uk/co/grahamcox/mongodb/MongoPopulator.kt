package uk.co.grahamcox.mongodb

import com.mongodb.DB
import org.springframework.core.io.Resource
import org.slf4j.LoggerFactory

/**
 * Mechanism to populate the given DB with sources from the given map
 * @param db The Database to populate
 * @param sources The map of collections to sources to populate them with
 */
class MongoPopulator(val db: DB, val sources: Map<String, Resource>) {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<MongoPopulator>())
    }

    fun populate() {
        for ((collection, source) in sources) {
            LOG.debug("Populating collection {} from source {}", collection, source)
        }
    }
}