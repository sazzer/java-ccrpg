package uk.co.grahamcox.ccrpg.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Test context for Spring Integration tests
 */
@Configuration
@ImportResource({
    "classpath:/test-context.xml"
})
public class TestContext {
}
