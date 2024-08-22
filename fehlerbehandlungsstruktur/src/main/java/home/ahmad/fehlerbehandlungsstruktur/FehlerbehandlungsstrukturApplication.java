package home.ahmad.fehlerbehandlungsstruktur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * http://localhost:8080/api/persons/99
 * 
 * @author Ahmad Alrefai
 */

@SpringBootApplication
public class FehlerbehandlungsstrukturApplication {
	private static final Logger log = LoggerFactory.getLogger(FehlerbehandlungsstrukturApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FehlerbehandlungsstrukturApplication.class, args);
	}

	 @Bean
	    public CommandLineRunner demo(PersonRepository repository) {
	        return (args) -> {
	            // Save many persons
	            repository.save(new Person("Ahmad", "Test"));
	            repository.save(new Person("Diana", "Test2"));

	            // Fetch all persons
	            log.info("Persons found with findAll():");
	            log.info("-------------------------------");
	            for (Person person : repository.findAll()) {
	                log.info(person.toString());
	            }
	        };
	    }
	}
