package uk.co.grahamcox.ccrpg.authentication.external.oauth2.mongo

import com.mongodb.DB
import uk.co.grahamcox.ccrpg.dao.BaseMongoDao
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.Config
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.OAuth2ConfigDao
import com.mongodb.BasicDBObject
import java.net.URI
import uk.co.grahamcox.ccrpg.dao.BadlyFormedRecordException

/**
 * Implementation of the OAuth2 Config DAO that works in terms of the MongoDB store
 * @param mongoDb The database connection
 */
class OAuth2ConfigMongoDao(mongoDb: DB) : BaseMongoDao(mongoDb, "externalAuthenticationConfig"), OAuth2ConfigDao {
    /**
     * Try and load the Google Authentication configuration
     * @param provider The name of the provider
     * @return the configuration
     * @throws NoRecordFoundException if the record wasn't found
     */
    override fun loadConfig(provider: String): Config {
        val record = collection.findOne(BasicDBObject("provider", provider))
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