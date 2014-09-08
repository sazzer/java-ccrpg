package uk.co.grahamcox.ccrpg

import org.spek.Spek
import org.springframework.http.MediaType
import org.junit.Assert
import org.springframework.mock.http.MockHttpInputMessage
import java.nio.charset.Charset

class TextPlainToMapHttpMessageConverterTest : Spek() {{
    given("The converter") {
        val converter = TextPlainToMapHttpMessageConverter()
        val supported = javaClass<java.util.Map<String, String>>()

        on("Checking what it supports") {
            it("Supports decoding into a Java Map") {
                Assert.assertTrue(converter.canRead(supported, MediaType.TEXT_PLAIN))
            }
            it("Doesn't support decoding into a String") {
                Assert.assertFalse(converter.canRead(javaClass<String>(), MediaType.TEXT_PLAIN))
            }
        }
        on("Decoding a valid string") {
            val inputMessage = MockHttpInputMessage("hello=world&answer=42".toByteArray(Charset.forName("UTF-8")))

            val converted = converter.read(supported, inputMessage)
                    ?: throw AssertionError("No converted value returned")

            it("Has two entries") {
                Assert.assertEquals(2, converted.size())
            }
            it("Has hello equal to world") {
                Assert.assertEquals("world", converted.get("hello"))
            }
            it("Has answer equal to 42") {
                Assert.assertEquals("42", converted.get("answer"))
            }
        }
    }
}}