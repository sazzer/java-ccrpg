package uk.co.grahamcox.mongodb

import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.process.runtime.Network
import de.flapdoodle.embed.mongo.MongodProcess

/**
 * Bean to start and stop the embedded MongoDB server
 */
class MongoWrapper(val port: Int) {
    /** The MongoDB process */
    var mongodProcess: MongodProcess? = null

    /**
     * Start MongoDB
     */
    fun start() {
        val starter = MongodStarter.getDefaultInstance()
        val config = MongodConfigBuilder()
            .version(Version.Main.PRODUCTION)
            ?.net(Net(port, Network.localhostIsIPv6()))
            ?.build();

        val mongodExecutable = starter?.prepare(config)
        mongodProcess = mongodExecutable?.start()
    }

    /**
     * Stop MongoDB
     */
    fun stop() {
        mongodProcess?.stop()
    }
}