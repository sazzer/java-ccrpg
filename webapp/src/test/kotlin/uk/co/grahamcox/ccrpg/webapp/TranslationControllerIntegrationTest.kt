package uk.co.grahamcox.ccrpg.webapp

import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.format.DateTimeFormatter
import org.junit.Test

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Integration test for the Translation Controller
 */
class TranslationControllerIntegrationTest : ControllerTestBase() {
    /**
     * Test the request to get the messages
     * @throws Exception never
     */
    [Test]
    public fun getMessages() {
        perform(get("/locales/translation/dev"))
                ?.andExpect(status()?.isOk())
                ?.andExpect(content()?.contentTypeCompatibleWith("application/json"))
                ?.andExpect(jsonPath("$.page.title")?.value("CCG RPG"))
    }

}