package home.ahmad.fehlerbehandlungsstruktur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Person mit ID " + id + " wurde nicht gefunden");
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        if (person.getFirstName() == null || person.getLastName() == null) {
            throw new InvalidRequestException("Vorname und Nachname dürfen nicht leer sein");
        }
        Person savedPerson = repository.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            repository.delete(person.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("Person mit ID " + id + " wurde nicht gefunden");
        }
    }
}
