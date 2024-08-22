package home.ahmad.boot_docker_deploy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * @author Ahmad Alrefai
 */


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> getAllPersons() {
    	 List<Person> persons = repository.findAll();
         
         System.out.println("___>found persons: " + persons);
         return persons;
    }
    
//    @GetMapping
//    public List<Person> getAllPersons() {
//        return Arrays.asList(new Person("John", "Doe"), new Person("Jane", "Doe"));
//    }
}
