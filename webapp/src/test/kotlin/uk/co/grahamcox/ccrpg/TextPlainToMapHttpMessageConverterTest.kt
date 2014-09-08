package uk.co.grahamcox.ccrpg

import org.springframework.http.MediaType
import org.junit.Assert
import org.springframework.mock.http.MockHttpInputMessage
import java.nio.charset.Charset
import kotlin.properties.Delegates
import org.junit.Before
import org.junit.Test

class TextPlainToMapHttpMessageConverterTest {
    class object {
        val SUPPORTED_CLASS = javaClass<java.util.Map<String, String>>()
    }
    var converter: TextPlainToMapHttpMessageConverter by Delegates.notNull()

    [Before]
    fun setup() {
        converter = TextPlainToMapHttpMessageConverter()
    }

    [Test]
    fun supportsMap() {
        Assert.assertTrue(converter.canRead(SUPPORTED_CLASS, MediaType.TEXT_PLAIN))
    }

    [Test]
    fun doesntSupportString() {
        Assert.assertFalse(converter.canRead(javaClass<String>(), MediaType.TEXT_PLAIN))
    }

    [Test]
    fun decodingString() {
        val inputMessage = MockHttpInputMessage("hello=world&answer=42".toByteArray(Charset.forName("UTF-8")))

        val converted = converter.read(SUPPORTED_CLASS, inputMessage)
                ?: throw AssertionError("No converted value returned")

        Assert.assertEquals(2, converted.size())
        Assert.assertEquals("world", converted.get("hello"))
        Assert.assertEquals("42", converted.get("answer"))
    }
}

