package uk.co.grahamcox.ccrpg.authentication.external

import org.easymock.EasyMock
import org.junit.Assert
import kotlin.properties.Delegates
import org.junit.Before
import org.junit.After
import org.junit.Test

class AuthenticationServiceTest {
    var authenticationService: AuthenticationService by Delegates.notNull()
    var authenticator: Authenticator by Delegates.notNull()

    [Before]
    fun setup() {
        authenticator = EasyMock.createMock(javaClass<Authenticator>()) ?: throw IllegalStateException()

        authenticationService = AuthenticationService()
        authenticationService.services = mapOf("authenticator" to authenticator)
    }

    [After]
    fun verify() {
        EasyMock.verify(authenticator)
    }

    [Test]
    fun missingAuthenticatorIsNotActive() {
        EasyMock.replay(authenticator)
        Assert.assertFalse(authenticationService.isActive("missing"))
    }

    [Test]
    fun disabledAuthenticatorIsNotActive() {
        EasyMock.expect(authenticator.isActive())
                ?.andReturn(false)
        EasyMock.replay(authenticator)
        Assert.assertFalse(authenticationService.isActive("authenticator"))
    }

    [Test]
    fun enabledAuthenticatorIsNotActive() {
        EasyMock.expect(authenticator.isActive())
                ?.andReturn(true)
        EasyMock.replay(authenticator)
        Assert.assertTrue(authenticationService.isActive("authenticator"))
    }

    [Test(expected = javaClass<UnsupportedProviderException>())]
    fun missingAuthenticatorFailsRedirectUri() {
        EasyMock.replay(authenticator)
        authenticationService.getRedirectUri("missing", Nonce("testing"))
    }

    [Test(expected = javaClass<UnsupportedProviderException>())]
    fun disabledAuthenticatorFailsRedirectUri() {
        EasyMock.expect(authenticator.isActive())
                ?.andReturn(false)
        EasyMock.replay(authenticator)
        authenticationService.getRedirectUri("authenticator", Nonce("testing"))
    }
}
