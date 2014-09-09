package uk.co.grahamcox.ccrpg.authentication.external.oauth2

import org.junit.Test
import org.junit.Assert
import uk.co.grahamcox.ccrpg.authentication.external.Nonce

class CallbackParamsTest {
    [Test]
    fun test() {
        val callbackParams = CallbackParams(mapOf(
            "state" to "ThisIsTheNonce",
            "code" to "ThisIsTheCode"
        ))

        Assert.assertEquals("ThisIsTheCode", callbackParams.authorizationCode)
        Assert.assertEquals(Nonce("ThisIsTheNonce"), callbackParams.nonce)
    }
}