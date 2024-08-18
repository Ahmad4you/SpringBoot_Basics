package home.ahmad.restHALbrowser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * http://localhost:8080/api/explorer/index.html#uri=/api
 * HAL Browser (Hypertext Application Language Browser)
 * 
 * [Thymeleaf Template] -> index.xhtml
 * das Thymeleaf-Template zeigt, wie die Daten im Frontend präsentiert werden können. 
 * Dies bietet eine robuste Lösung, um Passdaten zu speichern, zu suchen und anzuzeigen,
 * 
 * http://localhost:8080/api/primefaces
 * http://localhost:8080/api/primefaces/search/findByCountryOfIssueContaining?country=France
 * 
 * 
 * @author Ahmad Alrefai
 */
@SpringBootApplication
public class RestHaLbrowserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestHaLbrowserApplication.class, args);
	}

}
