package org.springframework.context.support

import java.util.Locale
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import kotlin.properties.Delegates

class ExposedMessageSourceTest {
    var messageSource: ExposedMessageSource by Delegates.notNull()
    [Before]
    fun setup() {
        messageSource = ExposedMessageSource()
        messageSource.setBasenames("classpath:/org/springframework/context/support/messages")
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setCacheSeconds(3)
        messageSource.setFallbackToSystemLocale(false)
    }

    [Test]
    fun supportedLocale() {
        val messages = messageSource.getAllMessages(Locale.forLanguageTag("en-GB"))

        Assert.assertEquals(3, messages.size)
        Assert.assertEquals("world", messages.get("hello"))
        Assert.assertEquals("42", messages.get("answer"))
        Assert.assertEquals("tea", messages.get("drink"))
    }
    [Test]
    fun unsupportedLocale() {
        val messages = messageSource.getAllMessages(Locale.forLanguageTag("ja-JP"))

        Assert.assertEquals(3, messages.size)
        Assert.assertEquals("world", messages.get("hello"))
        Assert.assertEquals("42", messages.get("answer"))
        Assert.assertEquals("coffee", messages.get("drink"))
    }
}
