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
        if (record == null) {
            throw NoRecordFoundException()
        }
        return Config(clientId = "380974699378-8l31mu6eo16gbbhq3ph6i4a6ibg3asrt.apps.googleusercontent.com",
                clientSecret = "7Ubw0_-WDRxVVZE113LfQp8Z",
                redirectUri = URI("http://localhost:8080/api/authentication/external/google/callback"),
                authorizationEndpoint = URI("https://accounts.google.com/o/oauth2/auth"),
                tokenEndpoint = URI("https://accounts.google.com/o/oauth2/token"))
    }
}