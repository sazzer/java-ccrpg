package uk.co.grahamcox.ccrpg.webapp.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus

/**
 * Controller for working with user accounts
 */
[Controller]
[RequestMapping(value = array("/api/user"))]
class UserController {
    /**
     * Handler for when an unsupported provider was requested
     *
     */
    [ExceptionHandler(javaClass<UserCreationException>())]
    [ResponseStatus(HttpStatus.BAD_REQUEST)]
    [ResponseBody]
    fun unsupportedProvider(e: UserCreationException) = e.getMessage() ?: "An unexpected error occurred"

    /**
     * Create a new user
     */
    [RequestMapping(method = array(RequestMethod.POST))]
    [ResponseBody]
    fun createUser(command: NewUserCommand): Any {
        throw UserCreationException("Screen name already in use")
    }
}