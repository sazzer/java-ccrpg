package uk.co.grahamcox.ccrpg.authentication.external.google

import org.spek.Spek
import org.junit.Assert


class JWTTest : Spek() {{
    given("the reference JWT") {
        val jwtString = "eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9." +
                "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ." +
                "dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk"

        on("decoding the JWT") {
            val jwt = JWT(jwtString)

            it("should have 'JWT' for the type") {
                Assert.assertEquals("JWT", jwt.jwtType)
            }
            it("should have 'HS256' for the algorithm") {
                    Assert.assertEquals("HS256", jwt.algorithm)
            }
            it("should have 'joe' for the Issuer") {
                    Assert.assertEquals("joe", jwt.issuer)
            }
            it("should have '1300819380' for the Expiration Time") {
                Assert.assertEquals(1300819380, jwt.expiration ?: null)
            }
        }
    }
}}