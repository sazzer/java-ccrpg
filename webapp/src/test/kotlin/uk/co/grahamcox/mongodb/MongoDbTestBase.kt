package uk.co.grahamcox.mongodb

import org.junit.Before
import org.junit.After
import org.slf4j.LoggerFactory
import java.net.ServerSocket

/**
 * Base class for MongoDb tests that will start and populate a MongoDB database before the test and tear it down
 * afterwards
 * @param seedData The data to populate the database with at startup
 */
open class MongoDbTestBase(val seedData: Map<String, String>? = null) {
    class object {
        val LOG = LoggerFactory.getLogger(javaClass<MongoDbTestBase>())
    }

    /** The port the mongo server runs on */
    protected val mongoPort: Int = ServerSocket(0).getLocalPort()

    /** The actual mongo wrapper */
    private val mongo = MongoWrapper(mongoPort)

    /**
     * Start the mongo server
     */
    [Before]
    fun startMongoDb() {
        LOG.info("Starting the database")
        mongo.start()

    }

    /**
     * Stop the mongo server
     */
    [After]
    fun stopMongoDb() {
        LOG.info("Stopping the database")
        mongo.stop()
    }

    /**
     * Populate the given collection with data from the given source file
     * @param collection the collection
     * @param source the source
     */
    protected fun populateDatabase(collection: String, source: String) {

    }

    /**
     * Populate the database with the given data
     * @param data map of collection to source file
     */
    protected fun populateDatabase(data: Map<String, String>) {
        for ((collection, source) in data) {
            populateDatabase(collection, source)
        }
    }
}