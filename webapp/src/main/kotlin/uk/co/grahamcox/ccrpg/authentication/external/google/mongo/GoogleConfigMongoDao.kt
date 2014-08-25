package uk.co.grahamcox.ccrpg.authentication.external.google.mongo

import com.mongodb.DB
import uk.co.grahamcox.ccrpg.dao.BaseMongoDao
import uk.co.grahamcox.ccrpg.authentication.external.google.Config
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import uk.co.grahamcox.ccrpg.authentication.external.google.GoogleConfigDao
import com.mongodb.BasicDBObject
import java.net.URI

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
                        clientId = record.getString("clientId") ?: throw NoRecordFoundException(),
                        clientSecret = record.getString("clientSecret") ?: throw NoRecordFoundException(),
                        redirectUri = URI(record.getString("redirectUri") ?: throw NoRecordFoundException()),
                        authorizationEndpoint = URI(record.getString("authorizationEndpoint") ?: throw NoRecordFoundException()),
                        tokenEndpoint = URI(record.getString("tokenEndpoint") ?: throw NoRecordFoundException())
                )
            else -> throw NoRecordFoundException()
        }
    }
}