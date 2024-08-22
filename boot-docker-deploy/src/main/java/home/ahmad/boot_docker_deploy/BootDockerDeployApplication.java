package home.ahmad.boot_docker_deploy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * curl -X GET http://localhost:8080/api/persons
 * 
 * @author Ahmad Alrefai
 */
@SpringBootApplication
public class BootDockerDeployApplication {

	 private static final Logger log = LoggerFactory.getLogger(BootDockerDeployApplication.class);

	    public static void main(String[] args) {
	        SpringApplication.run(BootDockerDeployApplication.class, args);
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