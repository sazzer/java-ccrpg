package uk.co.grahamcox.ccrpg.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**                                                                                                                                      [10/32]
 * Controller for getting the Home Page
 */
@Controller
@RequestMapping("/")
public class HomeController {
    /** The clock to use */
    private Clock clock;

    /**
     * Set the clock to use
     * @param clock the clock to use
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /**
     * Get the home page
     * @return the home page
     */
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView result = new ModelAndView("/home");
        result.addObject("now", 
            ZonedDateTime.now(clock).format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        return result;
    }
}
