package home.ahmad.restapiundjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.ahmad.restapiundjpa.model.Passport;
import home.ahmad.restapiundjpa.service.PassportService;

/**
 * 
 * @author Ahmad Alrefai
 */

@RestController
@RequestMapping("/api/passports")
public class PassportController {

    private final PassportService passportService;

    @Autowired
    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping
    public List<Passport> getAllPassports() {
        return passportService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> getPassportById(@PathVariable Long id) {
        return passportService.findById(id)
                .map(passport -> ResponseEntity.ok().body(passport))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Passport createPassport(@RequestBody Passport passport) {
        return passportService.save(passport);
    }

    @PutMapping("/{id}")
    public Passport updatePassport(@PathVariable Long id, @RequestBody Passport passport) {
        return passportService.update(id, passport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassport(@PathVariable Long id) {
        passportService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
