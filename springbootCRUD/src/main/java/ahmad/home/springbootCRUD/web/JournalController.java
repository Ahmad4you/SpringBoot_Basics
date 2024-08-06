package ahmad.home.springbootCRUD.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ahmad.home.springbootCRUD.model.Journal;
import ahmad.home.springbootCRUD.repository.JournalRepository;

import java.util.List;
import java.util.Optional;

/**
 * Holen aller Journale: http://localhost:8080/journal
 * Einzelnes Journal holen: http://localhost:8080/journal/{id}
 * 
 * curl -X POST http://localhost:8080/journal -H "Content-Type: application/json" 
 * -d '{"title": "My Title1", "summary": "My Summary1", "created": "08/06/2024"}'
 * 
 * curl -X POST http://localhost:8080/journal 
 * -H "Content-Type: application/json" 
 * -d "{\"title\": \"My Title1\", \"summary\": \"My Summary\", \"created\": \"08/06/2024\"}"
 * 
 * curl -X PUT http://localhost:8080/journal/{id} -H "Content-Type: application/json" 
 * -d '{"title": "Updated Title", "summary": "Updated Summary", "created": "08/06/2024"}'
 * 
 * curl -X GET http://localhost:8080/journal/1
 * 
 * curl -X DELETE http://localhost:8080/journal/{id}
 * 
 * 
 * @author Ahmad Alrefai
 */

//@RequestMapping("/journal")
@Controller
public class JournalController {

    @Autowired
    private JournalRepository repo;
    
    @RequestMapping(value = "/journal", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Journal> getJournal() {
        return repo.findAll();
    }
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("journal", repo.findAll());
        return "index";
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Journal> createJournal(@RequestBody Journal journal) {
        Journal savedJournal = repo.save(journal);
        return new ResponseEntity<>(savedJournal, HttpStatus.CREATED);
    }
    
    @GetMapping("/journal/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable Long id) {
        Optional<Journal> journal = repo.findById(id);
        return journal.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping("/journal/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable Long id, @RequestBody Journal updatedJournal) {
        return repo.findById(id).map(journal -> {
            journal.setTitle(updatedJournal.getTitle());
            journal.setSummary(updatedJournal.getSummary());
            journal.setCreated(updatedJournal.getCreated());
            repo.save(journal);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/journal/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
