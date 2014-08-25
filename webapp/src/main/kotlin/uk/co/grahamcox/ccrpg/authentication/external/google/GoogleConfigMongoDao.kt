package uk.co.grahamcox.ccrpg.authentication.external.google

import com.mongodb.DB
import com.mongodb.DBCollection

/**
 * DAO Class to access the Authentication config for the Google Authentication system
 * @param mongoDb The MongoDB connection
 */
class GoogleConfigMongoDao(mongoDb: DB) {
    /** The MongoDB Collection for the Authentication config */
    private val authConfigCollection: DBCollection;
    {
        authConfigCollection = mongoDb.getCollection("externalAuthenticationConfig") ?:
                throw IllegalStateException("Failed to get access to the config collection")
    }
}