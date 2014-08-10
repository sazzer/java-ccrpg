package uk.co.grahamcox.ccrpg.webapp

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.junit.runner.RunWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.junit.Before
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.http.HttpHeaders

/**
 * Base class for Webapp Integration tests
 */
[WebAppConfiguration]
[ActiveProfiles(array("test"))]
[RunWith(javaClass<SpringJUnit4ClassRunner>())]
[ContextConfiguration(classes = array(javaClass<TestContext>()))]
open class ControllerTestBase {
    /** Thw Web Application Context to use */
    [Autowired]
    private val webApplicationContext: WebApplicationContext? = null

    /** The Mock MVC Caller */
    private var mockMvc: MockMvc? = null

    /**
     * Set up the Mock MVC Test Support
     */
    [Before]
    public fun setupMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)?.build()
    }

    /**
     * Perform a request
     * @param request the request
     * @return the result of the request
     * @throws Exception if an error occurs
     */
    protected fun perform(request: MockHttpServletRequestBuilder?): ResultActions? {
        val httpHeaders = HttpHeaders()
        return mockMvc?.perform(request?.headers(httpHeaders))
    }

}