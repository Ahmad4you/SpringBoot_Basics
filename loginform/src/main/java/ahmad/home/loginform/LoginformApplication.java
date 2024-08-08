package ahmad.home.loginform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;


@SpringBootApplication
public class LoginformApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginformApplication.class, args);
	}
	
	@Configuration
	static class ConfigureJSFContextParameters implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) {

			servletContext.setInitParameter("jakarta.faces.DEFAULT_SUFFIX", ".xhtml");
			servletContext.setInitParameter("jakarta.faces.PARTIAL_STATE_SAVING_METHOD", "true");
			servletContext.setInitParameter("jakarta.faces.PROJECT_STAGE", "Development");
			servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
			servletContext.setInitParameter("jakarta.faces.FACELETS_REFRESH_PERIOD", "1");

		}
	}


}
