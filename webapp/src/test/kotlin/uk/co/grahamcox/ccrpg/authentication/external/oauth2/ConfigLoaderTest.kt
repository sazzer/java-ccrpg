package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import org.junit.Test
import kotlin.properties.Delegates
import org.junit.Before
import org.easymock.EasyMock
import org.junit.After
import java.net.URI
import org.junit.Assert

class ConfigLoaderTest {
    class object {
        val PROVIDER = "provider"
    }
    var configLoader: ConfigLoader by Delegates.notNull()
    var dao: OAuth2ConfigDao by Delegates.notNull()

    [Before]
    fun setup() {
        dao = EasyMock.createMock(javaClass<OAuth2ConfigDao>()) ?: throw IllegalStateException()

        configLoader = ConfigLoader()
        configLoader.dao = dao
        configLoader.provider = PROVIDER
    }

    [After]
    fun verify() {
        EasyMock.verify(dao)
    }

    [Test]
    fun testLoadConfig() {
        val configFromDao = Config("clientId",
                "clientSecret",
                URI("http://www.google.com"),
                URI("http://www.google.com"),
                URI("http://www.google.com"))
        EasyMock.expect(dao.loadConfig(PROVIDER))
            ?.andReturn(configFromDao)

        EasyMock.replay(dao)
        val config = configLoader.loadConfig()
        Assert.assertEquals(configFromDao, config)
    }
}