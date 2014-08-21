package uk.co.grahamcox.mongodb

import org.junit.Before
import org.junit.After
import org.slf4j.LoggerFactory
import java.net.ServerSocket


/**
 * Base class for MongoDb tests that will start and populate a MongoDB database before the test and tear it down
 * afterwards
 */
open class MongoDbTestBase {
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
}