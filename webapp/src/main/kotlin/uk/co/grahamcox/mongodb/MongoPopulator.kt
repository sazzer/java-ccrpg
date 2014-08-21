package uk.co.grahamcox.mongodb

import com.mongodb.DB
import org.springframework.core.io.Resource
import org.slf4j.LoggerFactory
import com.mongodb.BasicDBObject
import org.bson.types.ObjectId

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
        for ((collectionName, source) in sources) {
            LOG.debug("Populating collection {} from source {}", collectionName, source)
            val collection = db.getCollection(collectionName) ?:
                    throw IllegalStateException("Failed to get collection ${collectionName}")
            val data = loadData(source)
            for (record in data) {
                LOG.debug("Adding record {} to collection {}", data, collection)
                collection.insert(data)
            }
        }
    }

    /**
     * Load the data from the provided resource so that it can be stored to the database
     * @param source The source to load the data from
     */
    private fun loadData(source: Resource) : List<BasicDBObject> {
        return listOf(BasicDBObject()
                ?.append("_id", ObjectId())
                ?.append("name", "Graham")
                ?: throw IllegalArgumentException())
    }
}