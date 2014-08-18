package uk.co.grahamcox.ccrpg.authentication.external.google

import java.util.regex.Pattern
import java.util.Base64
import java.nio.charset.Charset
import org.slf4j.LoggerFactory
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Representation of a JSON Web Token
 */
data class JWT(jwt: String) {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<JWT>())
    }

    /** The header set */
    private val headerSet: Map<String, Any>
    /** The claims set */
    private val claimsSet: Map<String, Any>

    val jwtType: String?
        get() = headerSet.get("typ")?.toString()
    val algorithm: String?
        get() = headerSet.get("alg")?.toString()
    val issuer: String?
        get() = claimsSet.get("iss")?.toString()
    val subject: String?
        get() = claimsSet.get("sub")?.toString()
    val audience: String?
        get() = claimsSet.get("aud")?.toString()
    val expiration: Int?
        get() {
            val exp = claimsSet.get("exp")
            return when (exp) {
                is Int -> exp
                is String -> Integer.parseInt(exp)
                else -> null
            }
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

        val objectMapper = ObjectMapper()
        headerSet = objectMapper.readValue(header, javaClass<Map<String, Any>>()) ?:
                throw IllegalStateException("Failed to parse the JWT Header")
        claimsSet = objectMapper.readValue(claims, javaClass<Map<String, Any>>()) ?:
                throw IllegalStateException("Failed to parse the JWT Claims")
    }

    /**
     * Get the custom field with the given name
     * @param name The name of the field
     * @return the field value
     */
    fun field(name: String) = claimsSet.get(name)
}