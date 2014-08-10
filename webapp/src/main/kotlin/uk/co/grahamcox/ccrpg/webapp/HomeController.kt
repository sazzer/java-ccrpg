package uk.co.grahamcox.ccrpg.webapp

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import java.time.Clock
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Controller to load the home page
 */
[Controller]
[RequestMapping(value = array("/"))]
class HomeController(private val clock: Clock) {
    /**
     * Actually load the home page
     */
    [RequestMapping(value = array("/"))]
    fun home() : ModelAndView {
        val result = ModelAndView("/home")
        result.addObject("now", ZonedDateTime.now(clock)?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
        return result
    }
}