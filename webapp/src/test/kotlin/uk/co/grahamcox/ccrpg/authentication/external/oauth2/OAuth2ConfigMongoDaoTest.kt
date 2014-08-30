package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import uk.co.grahamcox.mongodb.MongoDbTestBase
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.mongo.OAuth2ConfigMongoDao
import org.junit.Before
import org.junit.Test
import uk.co.grahamcox.ccrpg.dao.NoRecordFoundException
import uk.co.grahamcox.ccrpg.dao.BadlyFormedRecordException
import kotlin.properties.Delegates
import org.junit.Assert
import java.net.URI

class OAuth2ConfigMongoDaoTest : MongoDbTestBase() {
    /** The DAO to test */
    var dao: OAuth2ConfigMongoDao by Delegates.notNull()

    /**
     * Set up the DAO to test
     */
    [Before]
    fun setup() {
        dao = OAuth2ConfigMongoDao(mongoDb)
    }

    /**
     * Test unsuccessfully loading the config
     */
    [Test(expected = javaClass<NoRecordFoundException>())]
    fun testLoadNoConfig() {
        dao.loadConfig("google")
    }
    /**
     * Test loading badly formed config
     */
    [Test(expected = javaClass<BadlyFormedRecordException>())]
    fun testLoadBadConfig() {
        populateDatabase("externalAuthenticationConfig",
                "/uk/co/grahamcox/ccrpg/authentication/external/oauth2/mongo/invalidAuthentication.json")
        dao.loadConfig("google")
    }
    /**
     * Test successfully loading the config
     */
    [Test]
    fun testLoadConfig() {
        populateDatabase("externalAuthenticationConfig",
                "/uk/co/grahamcox/ccrpg/authentication/external/oauth2/mongo/validAuthentication.json")
        val config = dao.loadConfig("google")
        Assert.assertEquals("TestClientId", config.clientId)
        Assert.assertEquals("TestClientSecret", config.clientSecret)
        Assert.assertEquals(URI("http://localhost:8080/api/authentication/external/google/callback"), config.redirectUri)
        Assert.assertEquals(URI("https://accounts.google.com/o/oauth2/auth"), config.authorizationEndpoint)
        Assert.assertEquals(URI("https://accounts.google.com/o/oauth2/token"), config.tokenEndpoint)
    }
}