package home.ahmad.boot_joinfaces.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author Ahmad Alrefai
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("forward:/login.faces");
        registry.addViewController("/index").setViewName("forward:/index.xhtml");
        registry.addViewController("/restCustomer").setViewName("forward:/restCustomer.xhtml");
    }
}