package home.ahmad.boot_joinfaces.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;

@Configuration
public class ConfigureJSFContextParameters implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {

		servletContext.setInitParameter("jakarta.faces.DEFAULT_SUFFIX", ".xhtml");
		servletContext.setInitParameter("jakarta.faces.PARTIAL_STATE_SAVING_METHOD", "true");
		servletContext.setInitParameter("jakarta.faces.PROJECT_STAGE", "Development");
		servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
		servletContext.setInitParameter("jakarta.faces.FACELETS_REFRESH_PERIOD", "1");

	}

}