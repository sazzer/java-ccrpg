package org.springframework.context.support

import java.util.Locale

/**
 * Extension of the Reloadable Resource Bundle message source that exposes all of the messages loaded
 */
class ExposedMessageSource : ReloadableResourceBundleMessageSource() {
    /**
     * Get all of the defined messages for the given locale
     * @param locale the locale to get the messages for
     * @return the messages
     */
    fun getAllMessages(locale : Locale?) : Map<Any, Any> {
        return getMergedProperties(locale)?.getProperties()?.toSortedMap().orEmpty()
    }
}