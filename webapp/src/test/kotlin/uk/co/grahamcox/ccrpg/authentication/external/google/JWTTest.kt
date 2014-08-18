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

    given("a real JWT") {
        val jwtString = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjhlZmMzZDM3MzUyMzhmYjE1YzVhOTMzNTQzMTEyYzczZGQ0OWIwNDUifQ." +
                "eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwic3ViIjoiMTE2NDQwMDk3NzE3NjkyNDk3MjY0IiwiYXpwIjoiMzgwOTc" +
                "0Njk5Mzc4LThsMzFtdTZlbzE2Z2JiaHEzcGg2aTRhNmliZzNhc3J0LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiZW" +
                "1haWwiOiJncmFoYW1AZ3JhaGFtY294LmNvLnVrIiwiYXRfaGFzaCI6Im1hUFloa2I1SUllX2x4OVhaSDhfOFEiLCJlbWFpb" +
                "F92ZXJpZmllZCI6dHJ1ZSwiYXVkIjoiMzgwOTc0Njk5Mzc4LThsMzFtdTZlbzE2Z2JiaHEzcGg2aTRhNmliZzNhc3J0LmFw" +
                "cHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJncmFoYW1jb3guY28udWsiLCJpYXQiOjE0MDgzNjMyNjYsImV4cCI" +
                "6MTQwODM2NzE2Nn0.g9NRDewQLaJrN_tzjcGsc6HWPM-RAjoxZ4SgixqkutDXdfNmHEGi07u5xKhj1-TRzTILAkfOvjuj47" +
                "J3unIat8UNN50AsM9mKbBhX34egPVifsUhNhioIurStwljb0fS89M-1ZeW9TZWjcMHv78q4-ZZWot1ETNfG25WFUNdf9w"
        on("decoding the JWT") {
            val jwt = JWT(jwtString)

            it("should have 'RS256' for the algorithm") {
                Assert.assertEquals("RS256", jwt.algorithm)
            }
            it("should have 'accounts.google.com' for the Issuer") {
                Assert.assertEquals("accounts.google.com", jwt.issuer)
            }
            it("should have '116440097717692497264' for the Subject") {
                Assert.assertEquals("116440097717692497264", jwt.subject)
            }
            it("should have '380974699378-8l31mu6eo16gbbhq3ph6i4a6ibg3asrt.apps.googleusercontent.com' for the Audience") {
                Assert.assertEquals("380974699378-8l31mu6eo16gbbhq3ph6i4a6ibg3asrt.apps.googleusercontent.com", jwt.audience)
            }
            it("should have '1408367166' for the Expiration Time") {
                Assert.assertEquals(1408367166, jwt.expiration ?: null)
            }
            it("should have 'graham@grahamcox.co.uk' as the custom field 'email'") {
                Assert.assertEquals("graham@grahamcox.co.uk", jwt.field("email"))
            }
        }
    }
}}