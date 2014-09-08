package uk.co.grahamcox.ccrpg.authentication.external.jwt

import org.junit.Assert
import org.junit.Test

class JWTTest {
    [Test]
    fun referenceJwt() {
        val jwtString = "eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9." +
                "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ." +
                "dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk"

        val jwt = JWT(jwtString)

        Assert.assertEquals("JWT", jwt.jwtType)
        Assert.assertEquals("HS256", jwt.algorithm)
        Assert.assertEquals("joe", jwt.issuer)
        Assert.assertEquals(1300819380, jwt.expiration ?: null)
    }

    [Test]
    fun realJwt() {
        val jwtString = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjhlZmMzZDM3MzUyMzhmYjE1YzVhOTMzNTQzMTEyYzczZGQ0OWIwNDUifQ." +
                "eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwic3ViIjoiMTE2NDQwMDk3NzE3NjkyNDk3MjY0IiwiYXpwIjoiMzgwOTc" +
                "0Njk5Mzc4LThsMzFtdTZlbzE2Z2JiaHEzcGg2aTRhNmliZzNhc3J0LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiZW" +
                "1haWwiOiJncmFoYW1AZ3JhaGFtY294LmNvLnVrIiwiYXRfaGFzaCI6Im1hUFloa2I1SUllX2x4OVhaSDhfOFEiLCJlbWFpb" +
                "F92ZXJpZmllZCI6dHJ1ZSwiYXVkIjoiMzgwOTc0Njk5Mzc4LThsMzFtdTZlbzE2Z2JiaHEzcGg2aTRhNmliZzNhc3J0LmFw" +
                "cHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJncmFoYW1jb3guY28udWsiLCJpYXQiOjE0MDgzNjMyNjYsImV4cCI" +
                "6MTQwODM2NzE2Nn0.g9NRDewQLaJrN_tzjcGsc6HWPM-RAjoxZ4SgixqkutDXdfNmHEGi07u5xKhj1-TRzTILAkfOvjuj47" +
                "J3unIat8UNN50AsM9mKbBhX34egPVifsUhNhioIurStwljb0fS89M-1ZeW9TZWjcMHv78q4-ZZWot1ETNfG25WFUNdf9w"
        val jwt = JWT(jwtString)

        Assert.assertEquals("RS256", jwt.algorithm)
        Assert.assertEquals("accounts.google.com", jwt.issuer)
        Assert.assertEquals("116440097717692497264", jwt.subject)
        Assert.assertEquals("380974699378-8l31mu6eo16gbbhq3ph6i4a6ibg3asrt.apps.googleusercontent.com", jwt.audience)
        Assert.assertEquals(1408367166, jwt.expiration ?: null)
        Assert.assertEquals("graham@grahamcox.co.uk", jwt.field("email"))
    }
}
