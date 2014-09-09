package uk.co.grahamcox.ccrpg.authentication.external.facebook

import org.junit.Test
import kotlin.properties.Delegates
import org.junit.Before
import org.springframework.web.client.RestTemplate
import org.easymock.EasyMock
import org.junit.After
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import java.net.URI
import org.junit.Assert

[Test]
class FacebookUserDetailsLoaderTest {
    private var testSubject: FacebookUserDetailsLoader by Delegates.notNull()

    private var restTemplate: RestTemplate by Delegates.notNull()

    [Before]
    fun setup() {
        restTemplate = EasyMock.createMock(javaClass<RestTemplate>()) ?: throw IllegalStateException()

        testSubject = FacebookUserDetailsLoader()
        testSubject.restTemplate = restTemplate
    }

    [After]
    fun verify() {
        EasyMock.verify(restTemplate)
    }

    [Test]
    fun getUserDetails() {
        EasyMock.expect(restTemplate.getForObject(
            URI("https://graph.facebook.com/me?access_token=ThisIsTheAccessToken"),
            javaClass<Map<String, String>>()))
            ?.andReturn(mapOf(
                "id" to "ThisIsTheUserId"
            ))

        EasyMock.replay(restTemplate)

        val accessToken = AccessTokenResponse(mapOf(
                "access_token" to "ThisIsTheAccessToken",
                "token_type" to "bearer"
        ))
        val user = testSubject.getUserDetails(accessToken) ?: throw AssertionError("No user returned")
        Assert.assertEquals("ThisIsTheUserId", user.id)
        Assert.assertEquals("facebook", user.source)

    }
}