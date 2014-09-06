package uk.co.grahamcox.ccrpg

import org.spek.Spek
import org.springframework.http.MediaType
import org.junit.Assert
import org.junit.Ignore

[Ignore]
class TextPlainToMapHttpMessageConverterTest : Spek() {{
    given("The converter") {
        val converter = TextPlainToMapHttpMessageConverter()

        on("Checking what it supports") {
            it("Supports decoding into a Java Map") {
                val supported = javaClass<java.util.Map<*, *>>()
                Assert.assertTrue(converter.canRead(supported, MediaType.MULTIPART_FORM_DATA))
            }
            it("Doesn't support decoding into a String") {
                Assert.assertFalse(converter.canRead(javaClass<String>(), MediaType.MULTIPART_FORM_DATA))
            }
        }
    }
}}