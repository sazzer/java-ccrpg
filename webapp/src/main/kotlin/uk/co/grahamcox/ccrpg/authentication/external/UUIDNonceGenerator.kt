package uk.co.grahamcox.ccrpg.authentication.external

import java.util.UUID

/**
 * Nonce Generator that generates a UUID for each requested nonce
 */
class UUIDNonceGenerator : NonceGenerator {
    /** {@inheritDoc} */
    override fun generate(): Nonce = Nonce(UUID.randomUUID().toString())
}