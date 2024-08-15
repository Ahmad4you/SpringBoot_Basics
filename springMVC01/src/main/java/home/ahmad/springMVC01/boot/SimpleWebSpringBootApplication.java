package home.ahmad.springMVC01.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/showMessage.html
 * 
 * @author Ahmad Alrefai
 */

@RestController
@SpringBootApplication
public class SimpleWebSpringBootApplication {

	@RequestMapping("/showMessage.html")
	public String index(){
		return "Spring Boot Rocks!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleWebSpringBootApplication.class, args);
	}
}
