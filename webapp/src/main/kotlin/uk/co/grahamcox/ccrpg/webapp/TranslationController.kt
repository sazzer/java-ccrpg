package uk.co.grahamcox.ccrpg.webapp

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import java.time.Clock
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.context.support.ExposedMessageSource
import java.util.Locale
import java.util.HashMap

/**
 * Controller to load the translations
 */
[Controller]
[RequestMapping(value = array("/locales/translation"))]
class TranslationController(private val messageSource: ExposedMessageSource) {
    /**
     * Actually load the requested translations
     * @param locale The locale to load
     */
    [RequestMapping(value = array("/{locale}"))]
    [ResponseBody]
    fun loadTranslations([PathVariable(value = "locale")] locale: String) : Map<String, Any> {
        val result = hashMapOf<String, Any>()

        messageSource.getAllMessages(Locale.forLanguageTag(locale)).forEach { it ->
            val key = it.key.toString().split("\\.")
            val fieldName = key.last()
            key.take(key.size - 1).fold(result) { a, b ->
                var next = hashMapOf<String, Any>()
                var current = a.get(b)

                if (current != null) {
                    if (current is HashMap<*, *>) {
                        next = current as HashMap<String, Any>
                    } else {
                        throw IllegalStateException("Message prefix exists as a message key: ${it}")
                    }
                } else {
                    a.put(b, next)
                }

                next
            }.put(fieldName, it.value)
        }

        return result
    }
}