package uk.co.grahamcox.ccrpg.webapp

import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.Configuration

/**
 * Test context for Spring Integration tests
 */
[Configuration]
[ImportResource(array("classpath:/test-context.xml"))]
open class TestContext()
