package home.ahmad.boot_actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.ahmad.boot_actuator.repository.PersonRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;


/**
 * Spring Boot Actuator stellt Produktions-Ready-Features wie Monitoring, Metriken und Health Checks zur Verfügung
 * Spring Boot Actuator: Ermöglicht Monitoring und Management von Anwendungen.
 * Endpoints und Konfigurationen: Die Konfigurationen in application.properties aktivieren und steuern verschiedene Actuator-Endpoints.
 * 
 * @author Ahmad Alrefai
 */

@RestController
@SpringBootApplication
public class BootActuatorApplication {
	private static final Logger log = LoggerFactory.getLogger(BootActuatorApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BootActuatorApplication.class, args);
	}
	/**
	 * Ein Spring Bean, der verwendet wird, um Metriken wie Zähler, Timer usw. zu erstellen und zu registrieren.
	 */
	@Autowired
	MeterRegistry meterRegistry;
	
	@RequestMapping("/") // Mappt die Root-URL / auf die Methode index()...
	public String index() {
        Counter counter = meterRegistry.counter("counter.index.invoked");
        counter.increment(); // Erhöht den Zähler, wenn der Endpoint aufgerufen wird.
        return "--> Spring Boot Actuator";
    }

    @Bean
    CommandLineRunner findAll(PersonRepository repo) {
        return args -> {
            log.info("> Persons in Database: ");
            repo.findAll().forEach(person -> log.info(person.toString()));
        };
    }
}
