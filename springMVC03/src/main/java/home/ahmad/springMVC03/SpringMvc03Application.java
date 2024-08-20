package home.ahmad.springMVC03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Blog-Anwendung, bei der Benutzer Beiträge erstellen, anzeigen, bearbeiten und löschen können
 * Jeder Beitrag kann mehrere Kommentare enthalten.
 * 
 * @author Ahmad Alrefai
 */
@SpringBootApplication
public class SpringMvc03Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvc03Application.class, args);
	}

}
