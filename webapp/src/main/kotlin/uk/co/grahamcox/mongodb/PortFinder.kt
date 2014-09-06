package uk.co.grahamcox.mongodb

import org.springframework.beans.factory.config.AbstractFactoryBean
import java.net.ServerSocket
import uk.co.grahamcox.LoggerFactory

/**
 * Find a free port to use
 */
class PortFinder : AbstractFactoryBean<Int>() {
    class object {
        /** The logger to use */
        val LOG = LoggerFactory.getLogger(javaClass<PortFinder>())
    }
    /** {@inheritDoc} */
    override fun getObjectType(): Class<out Any?>? {
        return javaClass<Int>()
    }
    /** {@inheritDoc} */
    override fun createInstance(): Int {
        val s = ServerSocket(0)
        val port = s.getLocalPort()
        LOG.debug("Using port: {}", port)
        return port
    }
}
