package uk.co.grahamcox.ccrpg.dao

import com.mongodb.DB
import com.mongodb.DBCollection

/**
 * Base class for MongoDB DAOs
 * @param mongoDb The MongoDB connection
 * @param collectionName The collection to work with
 */
open class BaseMongoDao(mongoDb: DB, collectionName: String) {
    /** The MongoDB Collection to use */
    protected val collection: DBCollection = mongoDb.getCollection(collectionName) ?:
                throw IllegalStateException("Failed to get access to the collection ${collectionName}")


}