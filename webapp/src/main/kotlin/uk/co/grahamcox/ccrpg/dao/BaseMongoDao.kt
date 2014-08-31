package uk.co.grahamcox.ccrpg.dao

import com.mongodb.DB
import com.mongodb.DBCollection
import kotlin.properties.Delegates

/**
 * Base class for MongoDB DAOs
 * @param collectionName The collection to work with
 */
open class BaseMongoDao(private val collectionName: String) {
    /** The MongoDB connection */
    var mongoDb: DB by Delegates.notNull()
    /** The MongoDB Collection to use */
    protected val collection: DBCollection
            get() = mongoDb.getCollection(collectionName) ?:
                throw IllegalStateException("Failed to get access to the collection ${collectionName}")


}