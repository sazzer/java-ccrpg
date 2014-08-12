package uk.co.grahamcox.ccrpg.webapp

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import java.time.Clock
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.PathVariable

/**
 * Controller to load the translations
 */
[Controller]
[RequestMapping(value = array("/locales/translation"))]
class TranslationController {
    /**
     * Actually load the requested translations
     * @param locale The locale to load
     */
    [RequestMapping(value = array("/{locale}"))]
    [ResponseBody]
    fun loadTranslations([PathVariable(value = "locale")] locale: String) : Map<String, Any> {
        val result = mapOf("hello" to locale, "world" to mapOf("earth" to "home", "mars" to "away"))
        return result
    }
}