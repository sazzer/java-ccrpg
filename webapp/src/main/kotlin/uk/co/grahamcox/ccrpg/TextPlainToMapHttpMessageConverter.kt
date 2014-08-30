package uk.co.grahamcox.ccrpg

import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.util.StreamUtils
import java.nio.charset.Charset
import org.springframework.http.MediaType
import java.util.Map

/**
 * HTTP Message Converter for converting a text/plain message to a Map<String, String>
 */
class TextPlainToMapHttpMessageConverter : AbstractHttpMessageConverter<Map<String, String>>(MediaType.TEXT_PLAIN) {
    /** The default default charset */
    public val DEFAULT_CHARSET: Charset = Charset.forName("ISO-8859-1")

    /** The default charset */
    private var defaultCharset: Charset = DEFAULT_CHARSET

    /** {@inheritDoc} */
    override fun supports(clazz: Class<out Any?>?): Boolean {
        val supported = javaClass<Map<*, *>>()

        return clazz?.isAssignableFrom(supported) ?: false
    }

    /** {@inheritDoc} */
    override fun readInternal(clazz: Class<out Map<String, String>>?, inputMessage: HttpInputMessage?):
            Map<String, String>? {

        val charset = getContentTypeCharset(inputMessage?.getHeaders()?.getContentType())
        val stringBody = StreamUtils.copyToString(inputMessage?.getBody(), charset) ?: ""

        val result = hashMapOf<String, String>()
        stringBody.split("&").forEach { param ->
            val splitParam = param.split("=", 2)
            result.put(splitParam[0], splitParam[1])
        }

        return result as Map<String, String>
    }
    /** {@inheritDoc} */
    override fun writeInternal(t: Map<String, String>?, outputMessage: HttpOutputMessage?) {
        throw UnsupportedOperationException()
    }

    /**
     * Get the charset from the content type
     * @param contentType the content type
     * @return the charset
     */
    private fun getContentTypeCharset(contentType: MediaType?): Charset? {
        if (contentType != null && contentType!!.getCharSet() != null) {
            return contentType!!.getCharSet()
        } else {
            return this.defaultCharset
        }
    }

}