package uk.co.grahamcox.mongodb

import org.junit.Rule
import org.junit.Test
import org.spek.Spek
import org.junit.Assert

/**
 * JUnit test to exercise the MongoDB Rule
 */
class MongoDbTestBaseTest : MongoDbTestBase(mapOf(
        "users" to "/uk/co/grahamcox/ccrpg/db/users.json"
)) {

    /**
     * A Test case
     */
    [Test]
    fun testMongoDb() {
    }
}
