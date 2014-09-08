package uk.co.grahamcox.ccrpg.authentication.external

import org.spek.Spek
import org.easymock.EasyMock
import org.junit.Assert

class AuthenticationServiceTest : Spek() {{
    val authenticationService = AuthenticationService()
    val disabledAuthenticator = EasyMock.createMock(javaClass<Authenticator>()) ?: throw IllegalStateException()
    EasyMock.expect(disabledAuthenticator.isActive())
        ?.andReturn(false)
        ?.anyTimes()

    val enabledAuthenticator = EasyMock.createMock(javaClass<Authenticator>()) ?: throw IllegalStateException()
    EasyMock.expect(enabledAuthenticator.isActive())
        ?.andReturn(true)
        ?.anyTimes()

    EasyMock.replay(disabledAuthenticator, enabledAuthenticator)
    authenticationService.services = mapOf("disabled" to disabledAuthenticator,
            "enabled" to enabledAuthenticator)

    given("A missing authenticator") {
        on("Checking if it is active") {
            val isActive = authenticationService.isActive("missing")
            it("Isn't active") {
                Assert.assertFalse(isActive)
            }
        }
        on("Getting the redirect URI") {
            it("Throws") {
                try {
                    authenticationService.getRedirectUri("missing", Nonce("Test"))
                    Assert.fail("Expected UnsupportedProviderException")
                } catch (e: UnsupportedProviderException) {
                    // Expected this
                }
            }
        }
    }

    given("A disabled authenticator") {
        on("Checking if it is active") {
            val isActive = authenticationService.isActive("disabled")
            it("Isn't active") {
                Assert.assertFalse(isActive)
            }
        }
        on("Getting the redirect URI") {
            it("Throws") {
                try {
                    authenticationService.getRedirectUri("disabled", Nonce("Test"))
                    Assert.fail("Expected UnsupportedProviderException")
                } catch (e: UnsupportedProviderException) {
                    // Expected this
                }
            }
        }
    }

    given("An enabled authenticator") {
        on("Checking if it is active") {
            val isActive = authenticationService.isActive("enabled")
            it("Is active") {
                Assert.assertTrue(isActive)
            }
        }
    }
}}