package home.ahmad.springMVC01.springmvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author Ahmad Alrefai
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hallo von Spring MVC!");
        return "hello"; // Dies sollte dem Namen JSP-Datei entsprechen (ohne .jsp)
    }
}
