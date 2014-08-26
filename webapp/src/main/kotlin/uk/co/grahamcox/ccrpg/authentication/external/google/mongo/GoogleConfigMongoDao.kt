package uk.co.grahamcox.ccrpg.authentication.external.google.mongo

import com.mongodb.DB
import uk.co.grahamcox.ccrpg.dao.BaseMongoDao
import uk.co.grahamcox.ccrpg.authentication.external.google.Config
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import uk.co.grahamcox.ccrpg.authentication.external.google.GoogleConfigDao
import com.mongodb.BasicDBObject
import java.net.URI
import uk.co.grahamcox.ccrpg.dao.BadlyFormedRecordException

/**
 * Implementation of the Google Config DAO that works in terms of the MongoDB store
 */
class GoogleConfigMongoDao(mongoDb: DB) : BaseMongoDao(mongoDb, "externalAuthenticationConfig"), GoogleConfigDao {
    /**
     * Try and load the Google Authentication configuration
     * @return the configuration
     * @throws NoRecordFoundException if the record wasn't found
     */
    override fun loadConfig(): Config {
        val record = collection.findOne(BasicDBObject("provider", "google"))
        return when (record) {
            is BasicDBObject -> Config(
                        clientId = record.getString("clientId") ?: throw BadlyFormedRecordException("Missing field: clientId"),
                        clientSecret = record.getString("clientSecret") ?: throw BadlyFormedRecordException("Missing field: clientSecret"),
                        redirectUri = URI(record.getString("redirectUri") ?: throw BadlyFormedRecordException("Missing field: redirectUri")),
                        authorizationEndpoint = URI(record.getString("authorizationEndpoint") ?: throw BadlyFormedRecordException("Missing field: authorizationEndpoint")),
                        tokenEndpoint = URI(record.getString("tokenEndpoint") ?: throw BadlyFormedRecordException("Missing field: tokenEndpoint"))
                )
            else -> throw NoRecordFoundException()
        }
    }
}