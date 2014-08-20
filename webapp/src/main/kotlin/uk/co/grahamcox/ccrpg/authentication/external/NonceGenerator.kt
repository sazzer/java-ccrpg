package uk.co.grahamcox.ccrpg.authentication.external

/**
 * Trait to describe a means to generate a Nonce
 */
trait NonceGenerator {
    /**
     * Generate a Nonce
     * @return the generated Nonce
     */
    fun generate() : Nonce
}