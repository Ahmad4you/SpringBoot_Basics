package home.ahmad.boot_jpa_jdbc;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import home.ahmad.boot_jpa_jdbc.model.User;
import home.ahmad.boot_jpa_jdbc.repository.UserRepository;

/**
 * 
 * @author Ahmad Alrefai
 */
@SpringBootApplication
public class SimpleJpaAppApplication {
	private static final Logger log = LoggerFactory.getLogger(SimpleJpaAppApplication.class);
	
	@Autowired
	UserRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleJpaAppApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner findAll(UserRepository repo){
//		return args -> {
//			log.info("> findAll() ...");
//			repo.findAll().forEach(entry -> log.info(entry.toString()));
//		};
//	}
	
	@Bean
	CommandLineRunner getOne(UserRepository repo){
	    return args -> {
	        log.info("> getOne() ...");
	        Optional<User> userOpt = repo.findById(2L);
	        if (userOpt.isPresent()) {
	            log.info(userOpt.get().toString());
	        } else {
	            log.info("User with ID 2 not found.");
	        }
	    };
	}
    
    @Bean
	CommandLineRunner findByCustomQuery(UserRepository repo){ //.. like %ahmad%"
		return args -> {
			log.info("> findByCustomQuery() ...");
			repo.findByCustomQuery("ahmad").forEach(entry -> log.info(entry.toString()));
		};
	}
    
    @Bean
    public CommandLineRunner testQueries(UserRepository userRepository) {
        return args -> {
            log.info("> Testing findByAgeGreaterThanEqual...");
            List<User> usersByAge = userRepository.findByAgeGreaterThanEqual(30);
            usersByAge.forEach(user -> log.info(user.toString()));

            log.info("> Testing findByAgeAndCountryOfIssue...");
            List<User> usersByAgeAndCountry = userRepository.findByAgeAndCountryOfIssue(33, "Germany");
            usersByAgeAndCountry.forEach(user -> log.info(user.toString()));
        };
    }
    
    
//    testen ohne CommandLineRunner
//    public static void testFindByAgeGreaterThanEqual() {
//        List<User> usersByAge = userRepository.findByAgeGreaterThanEqual(30);
//        usersByAge.forEach(user -> System.out.println(user.toString()));
//    }
//
//    public static void testFindByAgeAndCountryOfIssue() {
//        List<User> usersByAgeAndCountry = userRepository.findByAgeAndCountryOfIssue(33, "Germany");
//        usersByAgeAndCountry.forEach(user -> System.out.println(user.toString()));
//    }
}