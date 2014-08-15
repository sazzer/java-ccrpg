package uk.co.grahamcox.ccrpg.webapp.authentication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controller to provide access to external authentication services
 */
[Controller]
[RequestMapping(value = array("/api/authentication/external"))]
class ExternalAuthenticationController {

    /**
     * Get the list of authentication providers that are supported
     * @return the list of providers
     */
    [RequestMapping]
    [ResponseBody]
    fun listProviders(): Map<String, String> {
        val results = linkedMapOf(
                Pair("facebook", "Facebook"),
                Pair("google", "Google+"),
                Pair("twitter", "Twitter"))

        return results
    }

}