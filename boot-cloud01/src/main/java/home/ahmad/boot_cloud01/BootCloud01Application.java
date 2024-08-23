package home.ahmad.boot_cloud01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import home.ahmad.boot_cloud01.model.Employee;
import home.ahmad.boot_cloud01.repository.EmployeeRepository;

/**
 * 
 * @author Ahmad Alrefai
 */
@SpringBootApplication
public class BootCloud01Application {
	private static final Logger log = LoggerFactory.getLogger(BootCloud01Application.class);
	public static void main(String[] args) {
		SpringApplication.run(BootCloud01Application.class, args);
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
