package uk.co.grahamcox.ccrpg.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**                                                                                                                                      [10/32]
 * Controller for getting Debug information
 */
@Controller
@RequestMapping("/api/debug")
public class DebugController {
    /** The clock to use */
    private Clock clock;

    /** The build information */
    private Properties buildInfo;

    /**
     * Construct the controller, loading the build information
     */
    public DebugController() throws IOException {
        InputStream properties = getClass().getResourceAsStream("/build.info");
        buildInfo = new Properties();
        buildInfo.load(properties);
    }

    /**
     * Set the clock to use
     * @param clock the clock to use
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /**
     * Get the current time according to the system
     * @return the current time
     */
    @RequestMapping("/now")
    @ResponseBody
    public String getCurrentTime() {
        return ZonedDateTime.now(clock).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    /**
     * Get the build information
     * @return the build information
     */
    @RequestMapping("/buildInfo")
    @ResponseBody
    public Properties getBuildInfo() {
        return buildInfo;
    }
}
