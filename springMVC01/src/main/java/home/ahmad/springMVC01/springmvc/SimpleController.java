package home.ahmad.springMVC01.springmvc;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author Ahmad Alrefai
 */
@Controller
public class SimpleController {

    @GetMapping("/showMessage")
    public ModelAndView handleRequest() {
        ModelAndView model = new ModelAndView("showMessage");
        model.addObject("message", "Spring MVC Web Application");
        return model;
    }
}