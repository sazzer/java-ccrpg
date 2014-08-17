package uk.co.grahamcox.ccrpg.authentication.external.google

import java.util.regex.Pattern
import java.util.Base64
import java.nio.charset.Charset
import org.slf4j.LoggerFactory

/**
 * Representation of a JSON Web Token
 */
data class JWT(jwt: String) {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<JWT>())
    }

    {
        LOG?.debug("About to parse JWT {}", jwt)
        val splitJwt = jwt.split("\\.")

        LOG?.debug("JWT Parts: Header {}, Claims {}, Signature {}", splitJwt[0], splitJwt[1], splitJwt[2])
        
        val decoder = Base64.getUrlDecoder()
        if (decoder == null) {
            throw IllegalStateException("Failed to get the Base64 Decoder")
        }

        val header = String(
                decoder.decode(splitJwt[0]) ?: throw IllegalStateException("Failed to decode the JWT Header"),
                Charset.forName("UTF-8"))
        val claims = String(
                decoder.decode(splitJwt[1]) ?: throw IllegalStateException("Failed to decode the JWT Claims"),
                Charset.forName("UTF-8"))
        LOG?.debug("Decoded JWT header: {}, claims: {}", header, claims)
    }
}