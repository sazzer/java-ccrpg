package uk.co.grahamcox.ccrpg.webapp.authentication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.ccrpg.authentication.external.AuthenticationService
import java.util.HashMap
import org.springframework.web.bind.annotation.PathVariable
import uk.co.grahamcox.ccrpg.authentication.external.NonceGenerator
import org.springframework.web.bind.annotation.ExceptionHandler
import uk.co.grahamcox.ccrpg.authentication.external.UnsupportedProviderException
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView

/**
 * Controller to provide access to external authentication services
 * @param authenticationService the authentication service to use
 * @param nonceGenerator The means to generate the nonce for authentication
 */
[Controller]
[RequestMapping(value = array("/api/authentication/external"))]
class ExternalAuthenticationController(val authenticationService: AuthenticationService,
                                       val nonceGenerator: NonceGenerator) {

    /**
     * Handler for when an unsupported provider was requested
     *
     */
    [ExceptionHandler(javaClass<UnsupportedProviderException>())]
    [ResponseStatus(HttpStatus.NOT_FOUND)]
    [ResponseBody]
    fun unsupportedProvider(e: UnsupportedProviderException) = "Unsupported authentication service"

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
     * @param provider The name of the provider
     * @return the redirect to the provider
     */
    [RequestMapping(array("/{provider}"))]
    fun redirect([PathVariable("provider")]provider: String): String {
        val nonce = nonceGenerator.generate()
        val redirectUri = authenticationService.getRedirectUri(provider, nonce)
        return "redirect:${redirectUri}"
    }

    /**
     * Handle the callback from an external authentication provider
     * @param provider The name of the provider
     * @param webRequest The request to get the params from
     * @return the model and view for the authenticated callback page
     */
    [RequestMapping(array("/{provider}/callback"))]
    fun callback([PathVariable("provider")]provider: String, webRequest: WebRequest): ModelAndView {
        val nonce = nonceGenerator.generate()
        val params = hashMapOf<String, String>()
        for (param: String in webRequest.getParameterNames()) {
            val paramValue = webRequest.getParameter(param)
            if (paramValue != null) {
                params.put(param, paramValue)
            }
        }
        val user = authenticationService.handleCallback(provider, nonce, params)

        val result = ModelAndView("/authcallback")
        result.addObject("user", user)
        result.addObject("newUser", true)
        return result
    }

}