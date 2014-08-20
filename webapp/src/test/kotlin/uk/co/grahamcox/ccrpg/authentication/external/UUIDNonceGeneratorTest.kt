package uk.co.grahamcox.ccrpg.authentication.external

import org.spek.Spek
import org.junit.Assert
import java.util.regex.Pattern

class ExposedMessageSourceTest : Spek() {{
    given("a UUID Nonce Generator") {
        val nonceGenerator = UUIDNonceGenerator()

        on("generating a Nonce") {
            val nonce = nonceGenerator.generate()

            it("should return a value that looks like a UUID") {
                val uuidPattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
                Assert.assertTrue(uuidPattern.matcher(nonce.value).matches())
            }
        }
    }
}}