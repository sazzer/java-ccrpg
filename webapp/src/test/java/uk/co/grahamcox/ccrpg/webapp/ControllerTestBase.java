package uk.co.grahamcox.ccrpg.webapp;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Base class for Webapp Integration tests
 */
@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class ControllerTestBase {
    /** Thw Web Application Context to use */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /** The Mock MVC Caller */
    private MockMvc mockMvc;

    /**
     * Set up the Mock MVC Test Support
     */
    @Before
    public void setupMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Get the Mock MVC Test Support
     * @return the Mock MVC Test Support
     */
    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    /**
     * Perform a request
     * @param request the request
     * @return the result of the request
     * @throws Exception if an error occurs
     */
    protected ResultActions perform(MockHttpServletRequestBuilder request) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        return getMockMvc().perform(request.headers(httpHeaders));
    }
}
