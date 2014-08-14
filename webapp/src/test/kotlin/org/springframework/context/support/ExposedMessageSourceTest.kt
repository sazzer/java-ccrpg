package org.springframework.context.support

import org.spek.Spek
import java.util.Locale
import org.junit.Assert

class ExposedMessageSourceTest : Spek() {{
    given("a Message Source") {
        val messageSource = ExposedMessageSource()
        messageSource.setBasenames("classpath:/org/springframework/context/support/messages")
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setCacheSeconds(3)
        messageSource.setFallbackToSystemLocale(false)

        on("calling getAllMessages with a supported locale") {
            val messages = messageSource.getAllMessages(Locale.forLanguageTag("en-GB"))

            it("should return 3 messages") {
                Assert.assertEquals(3, messages.size)
            }
            it("should have the correct value for 'hello") {
                Assert.assertEquals("world", messages.get("hello"))
            }
            it("should have the correct value for 'answer") {
                Assert.assertEquals("42", messages.get("answer"))
            }
            it("should have the correct value for 'drink") {
                Assert.assertEquals("tea", messages.get("drink"))
            }
        }
        on("calling getAllMessages with an unsupported locale") {
            val messages = messageSource.getAllMessages(Locale.forLanguageTag("ja-JP"))
            it("should return 3 messages") {
                Assert.assertEquals(3, messages.size)
            }
            it("should have the correct value for 'hello") {
                Assert.assertEquals("world", messages.get("hello"))
            }
            it("should have the correct value for 'answer") {
                Assert.assertEquals("42", messages.get("answer"))
            }
            it("should have the correct value for 'drink") {
                Assert.assertEquals("coffee", messages.get("drink"))
            }
        }
    }
}}