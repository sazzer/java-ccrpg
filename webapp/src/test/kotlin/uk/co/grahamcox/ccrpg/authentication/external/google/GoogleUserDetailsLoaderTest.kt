package uk.co.grahamcox.ccrpg.authentication.external.google

import org.junit.Test
import uk.co.grahamcox.ccrpg.authentication.external.oauth2.AccessTokenResponse
import org.junit.Assert

class GoogleUserDetailsLoaderTest {
    [Test]
    fun testWithValidJwt() {
        val jwtString = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjhlZmMzZDM3MzUyMzhmYjE1YzVhOTMzNTQzMTEyYzczZGQ0OWIwNDUifQ." +
                "eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwic3ViIjoiMTE2NDQwMDk3NzE3NjkyNDk3MjY0IiwiYXpwIjoiMzgwOTc" +
                "0Njk5Mzc4LThsMzFtdTZlbzE2Z2JiaHEzcGg2aTRhNmliZzNhc3J0LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiZW" +
                "1haWwiOiJncmFoYW1AZ3JhaGFtY294LmNvLnVrIiwiYXRfaGFzaCI6Im1hUFloa2I1SUllX2x4OVhaSDhfOFEiLCJlbWFpb" +
                "F92ZXJpZmllZCI6dHJ1ZSwiYXVkIjoiMzgwOTc0Njk5Mzc4LThsMzFtdTZlbzE2Z2JiaHEzcGg2aTRhNmliZzNhc3J0LmFw" +
                "cHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJncmFoYW1jb3guY28udWsiLCJpYXQiOjE0MDgzNjMyNjYsImV4cCI" +
                "6MTQwODM2NzE2Nn0.g9NRDewQLaJrN_tzjcGsc6HWPM-RAjoxZ4SgixqkutDXdfNmHEGi07u5xKhj1-TRzTILAkfOvjuj47" +
                "J3unIat8UNN50AsM9mKbBhX34egPVifsUhNhioIurStwljb0fS89M-1ZeW9TZWjcMHv78q4-ZZWot1ETNfG25WFUNdf9w"

        val loader = GoogleUserDetailsLoader()

        val accessToken = AccessTokenResponse(mapOf(
                "access_token" to "ThisIsTheAccessToken",
                "token_type" to "bearer",
                "id_token" to jwtString
        ))
        val user = loader.getUserDetails(accessToken)
        Assert.assertEquals("google", user.source)
        Assert.assertEquals("116440097717692497264", user.id)
    }

    [Test(expected = javaClass<IllegalStateException>())]
    fun testWithInvalidJwt() {
        val jwtString = "eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9." +
                "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ." +
                "dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk"

        val loader = GoogleUserDetailsLoader()

        val accessToken = AccessTokenResponse(mapOf(
                "access_token" to "ThisIsTheAccessToken",
                "token_type" to "bearer",
                "id_token" to jwtString
        ))
        loader.getUserDetails(accessToken)
    }

    [Test(expected = javaClass<IllegalStateException>())]
    fun testWithMissingJwt() {
        val loader = GoogleUserDetailsLoader()

        val accessToken = AccessTokenResponse(mapOf(
                "access_token" to "ThisIsTheAccessToken",
                "token_type" to "bearer"
        ))
        loader.getUserDetails(accessToken)
    }
}