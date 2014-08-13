package uk.co.grahamcox.ccrpg.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Clock
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.springframework.web.bind.annotation.ResponseBody
import java.util.Properties
import org.springframework.core.io.Resource

/**
 * Controller to load the home page
 */
[Controller]
[RequestMapping(value = array("/api/debug"))]
class DebugController(private val clock: Clock, private val buildInfo: Resource) {
    private val properties = Properties();

    {
        properties.load(buildInfo.getInputStream())
    }

    /**
     * Get the current time according to the system
     * @return the current time
     */
    [RequestMapping(value = array("/now"))]
    [ResponseBody]
    fun getCurrentTime(): String? =
            ZonedDateTime.now(clock)?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

    /**
     * Get the build information
     * @return the build information
     */
    [RequestMapping(value = array("/buildInfo"))]
    [ResponseBody]
    fun getBuildInfo() = properties
}