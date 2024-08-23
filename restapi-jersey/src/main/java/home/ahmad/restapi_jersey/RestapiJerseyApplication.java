package home.ahmad.restapi_jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import home.ahmad.restapi_jersesy.controller.EmployeeController;
import home.ahmad.restapi_jersesy.service.EmployeeService;
import home.ahmad.restapi_jersey.model.Employee;
import home.ahmad.restapi_jersey.repository.EmployeeRepository;

/**
 * HATEOAS (Hypermedia as the Engine of Application State) zur Verbesserung der Hypermedia-Verlinkungen in den Antworten der API.
 * HATEOAS nur mit MVC-controller verwenden und nicht mit den jersey
 * 
 * ich habe hier nur einen jersey-controller implementiert
 * 
 * @author Ahmad Alrefai
 */
@SpringBootApplication
@ComponentScan(basePackages = "home.ahmad.restapi_jersesy")
public class RestapiJerseyApplication {
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	public static void main(String[] args) {
		SpringApplication.run(RestapiJerseyApplication.class, args);
	}
	
	@Bean
    public ResourceConfig resourceConfig(EmployeeService employeeService) {
        ResourceConfig config = new ResourceConfig();
        config.register(new EmployeeController(employeeService));  // Manuelles Registrieren des Controllers mit dem Service
        return config;
    }
	
	@Bean
    public CommandLineRunner demo(EmployeeRepository repository) {
        return (args) -> {
            // Save many persons
            repository.save(new Employee( 1L, "Ahmad", "Admin"));
            repository.save(new Employee( 2L, "Diana", "Datenschutz"));

            // Fetch all persons
            log.info("Employees found with findAll():");
            log.info("-------------------------------");
            for (Employee employee : repository.findAll()) {
                log.info(employee.toString());
            }
        };
    }

}
