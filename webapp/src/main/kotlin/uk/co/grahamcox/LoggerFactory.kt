package uk.co.grahamcox

class LoggerFactory {
    class object {
        /**
         * Get the logger to use for the given class
         * @param cls The class to get the logger for
         * @return the logger
         */
        fun getLogger(cls: Class<out Any?>) =
                org.slf4j.LoggerFactory.getLogger(cls) ?: throw IllegalStateException("No logger returned")
    }
}
