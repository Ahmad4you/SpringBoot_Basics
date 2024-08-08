package ahmad.home.loginform.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.ServletContext;

/**
 * 
 * @author Ahmad Alrefai
 */
@Configuration
public class ConfigureJSF {

	@Bean
	ServletRegistrationBean<FacesServlet> jsfServletRegistration(ServletContext servletContext) {
		//spring boot only works if this is set
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

		//FacesServlet registration
		ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<FacesServlet>();
		srb.setServlet(new FacesServlet());
		srb.setUrlMappings(Arrays.asList("*.xhtml"));
		srb.setLoadOnStartup(1);
		return srb;
	}
}
