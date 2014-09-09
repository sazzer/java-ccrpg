package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import org.junit.Test
import org.junit.Assert

class AccessTokenResponseTest {
    [Test]
    fun test() {
        val accessToken = AccessTokenResponse(mapOf(
            "access_token" to "ThisIsTheAccessToken",
            "token_type" to "bearer",
            "jwt" to "ThisIsSomethingElse"
        ))

        Assert.assertEquals("ThisIsTheAccessToken", accessToken.accessToken)
        Assert.assertEquals("bearer", accessToken.tokenType)
        Assert.assertEquals("ThisIsSomethingElse", accessToken.getParam("jwt"))
    }
}