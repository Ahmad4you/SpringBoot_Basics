package ahmad.home.springbootCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootCrudApplication {
	
//	@Bean
//	InitializingBean saveData(JournalRepository repo){
//		return () -> {
//			repo.save(new Journal("Get to know Spring Boot","Today Spring Boot","01/01/2024"));
//			repo.save(new Journal("Spring Boot Project","I will do my first Spring Boot Project","01/02/2024"));
//			repo.save(new Journal("Spring Boot Reading","more about Spring Boot","02/01/2024"));
//			repo.save(new Journal("Spring Boot in the Cloud","Spring Boot using Cloud Foundry","03/01/2024"));
//		};
//	}
//	
//	
//	
//	@Bean
//	CommandLineRunner init(JournalRepository repo){
//		return args -> {
//			System.out.println("---------------------------------");
//	        repo.findAll().forEach(System.out::println);
//	        System.out.println("---------------------------------");
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudApplication.class, args);
	}

}
