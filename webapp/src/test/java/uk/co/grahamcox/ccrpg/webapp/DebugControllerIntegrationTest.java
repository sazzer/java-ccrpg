package uk.co.grahamcox.ccrpg.webapp;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for the Debug Controller
 */
public class DebugControllerIntegrationTest extends ControllerTestBase {
    /**
     * Test the request to get the current time
     * @throws Exception never
     */
    @Test
    public void getNow() throws Exception {
        MvcResult mvcResult = perform(get("/api/debug/now"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("text/plain"))
            .andReturn();

        // Ensure that what we got actually parses
        String contentAsString = mvcResult.getResponse().getContentAsString();
        DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(contentAsString);
    }

    /**
     * Test the request to get the build info
     * @throws Exception never
     */
    @Test
    public void getBuildInfo() throws Exception {
        perform(get("/api/debug/buildInfo"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$.[project.name]").value("CCG RPG - Webapp"))
            .andExpect(jsonPath("$.[project.version]").value("1.0-SNAPSHOT"))
            .andExpect(jsonPath("$.[build.time]").value("22 July 2014, 12:14:54 +01:00"))
            .andExpect(jsonPath("$.[git.revision]").value("d526e8dd132dfc5b9066993c5992770573932378"));
    }
}