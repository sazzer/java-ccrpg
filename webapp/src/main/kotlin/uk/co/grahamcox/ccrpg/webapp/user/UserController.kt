package uk.co.grahamcox.ccrpg.webapp.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody

/**
 * Controller for working with user accounts
 */
[Controller]
[RequestMapping(value = array("/api/user"))]
class UserController {
    /**
     * Create a new user
     */
    [RequestMapping(method = array(RequestMethod.POST))]
    [ResponseBody]
    fun createUser(command: NewUserCommand): Any {
        return command
    }
}