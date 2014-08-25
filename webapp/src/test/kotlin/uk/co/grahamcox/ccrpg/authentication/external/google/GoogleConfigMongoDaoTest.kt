package uk.co.grahamcox.ccrpg.authentication.external.google

import uk.co.grahamcox.mongodb.MongoDbTestBase
import uk.co.grahamcox.ccrpg.authentication.external.google.mongo.GoogleConfigMongoDao
import kotlin.properties.Delegates
import org.junit.Before
import org.junit.Test
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import org.junit.Assert
import java.net.URI
import uk.co.grahamcox.ccrpg.dao.BadlyFormedRecordException

/**
 * Unit tests for the Google Config Mongo DAO
 */
class GoogleConfigMongoDaoTest : MongoDbTestBase() {
    /** The DAO to test */
    var dao: GoogleConfigMongoDao by Delegates.notNull()

    /**
     * Set up the DAO to test
     */
    [Before]
    fun setup() {
        dao = GoogleConfigMongoDao(mongoDb)
    }

    /**
     * Test unsuccessfully loading the config
     */
    [Test(expected = javaClass<NoRecordFoundException>())]
    fun testLoadNoConfig() {
        dao.loadConfig()
    }
    /**
     * Test loading badly formed config
     */
    [Test(expected = javaClass<BadlyFormedRecordException>())]
    fun testLoadBadConfig() {
        populateDatabase("externalAuthenticationConfig",
                "/uk/co/grahamcox/ccrpg/authentication/external/google/mongo/invalidAuthentication.json")
        dao.loadConfig()
    }
    /**
     * Test successfully loading the config
     */
    [Test]
    fun testLoadConfig() {
        populateDatabase("externalAuthenticationConfig",
                "/uk/co/grahamcox/ccrpg/authentication/external/google/mongo/validAuthentication.json")
        val config = dao.loadConfig()
        Assert.assertEquals("TestClientId", config.clientId)
        Assert.assertEquals("TestClientSecret", config.clientSecret)
        Assert.assertEquals(URI("http://localhost:8080/api/authentication/external/google/callback"), config.redirectUri)
        Assert.assertEquals(URI("https://accounts.google.com/o/oauth2/auth"), config.authorizationEndpoint)
        Assert.assertEquals(URI("https://accounts.google.com/o/oauth2/token"), config.tokenEndpoint)
    }
}