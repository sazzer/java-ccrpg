package uk.co.grahamcox.ccrpg.webapp.authentication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticationService
import java.util.HashMap
import org.springframework.web.bind.annotation.PathVariable

/**
 * Controller to provide access to external authentication services
 */
[Controller]
[RequestMapping(value = array("/api/authentication/external"))]
class ExternalAuthenticationController(val authenticationService: AuthenticationService) {

    /**
     * Get the list of authentication providers that are supported
     * @return the list of providers
     */
    [RequestMapping]
    [ResponseBody]
    fun listProviders(): Collection<String> {
        return authenticationService.getActiveServices().sort()
    }

    /**
     * Redirect the user to the authentication endpoint for the requested provider
     * @return the redirect to the provider
     */
    [RequestMapping(array("/{provider}"))]
    fun redirect([PathVariable("provider")]provider: String): String {
        return "redirect:http://www.google.com?q=" + provider
    }

}