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
 * Helper to load the build info from the provided resource
 */
fun loadBuildInfo(buildInfo: Resource) : Properties {
    val properties = Properties()
    properties.load(buildInfo.getInputStream())
    return properties
}
/**
 * Controller to load the home page
 */
[Controller]
[RequestMapping(value = array("/api/debug"))]
class DebugController(private val clock: Clock, buildInfo: Resource) {
    private val properties = loadBuildInfo(buildInfo)
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