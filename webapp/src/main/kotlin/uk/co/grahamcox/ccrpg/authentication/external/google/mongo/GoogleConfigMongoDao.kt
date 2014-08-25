package uk.co.grahamcox.ccrpg.authentication.external.google.mongo

import com.mongodb.DB
import uk.co.grahamcox.ccrpg.dao.BaseMongoDao
import uk.co.grahamcox.ccrpg.authentication.external.google.Config
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import uk.co.grahamcox.ccrpg.authentication.external.google.GoogleConfigDao

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
        throw NoRecordFoundException()
    }
}