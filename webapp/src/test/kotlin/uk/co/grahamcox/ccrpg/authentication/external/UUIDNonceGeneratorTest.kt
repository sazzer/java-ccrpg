package uk.co.grahamcox.ccrpg.authentication.external

import org.junit.Assert
import java.util.regex.Pattern
import org.junit.Test

class UUIDNonceGeneratorTest {
    [Test]
    fun generate() {
        val nonceGenerator = UUIDNonceGenerator()

        val nonce = nonceGenerator.generate()
        val uuidPattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
        Assert.assertTrue(uuidPattern.matcher(nonce.value).matches())
   }
}
