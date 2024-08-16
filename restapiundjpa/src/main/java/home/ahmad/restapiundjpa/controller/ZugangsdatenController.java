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

import home.ahmad.restapiundjpa.model.Zugangsdaten;
import home.ahmad.restapiundjpa.service.ZugangsdatenService;

/**
 * 
 * @author Ahmad Alrefai
 */

@RestController
@RequestMapping("/api/zugangsdaten")
public class ZugangsdatenController {

    private final ZugangsdatenService zugangsdatenService;

    @Autowired
    public ZugangsdatenController(ZugangsdatenService zugangsdatenService) {
        this.zugangsdatenService = zugangsdatenService;
    }

    @GetMapping
    public List<Zugangsdaten> getAllZugangsdaten() {
        return zugangsdatenService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zugangsdaten> getZugangsdatenById(@PathVariable Long id) {
        return zugangsdatenService.findById(id)
                .map(zugangsdaten -> ResponseEntity.ok().body(zugangsdaten))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Zugangsdaten createZugangsdaten(@RequestBody Zugangsdaten zugangsdaten) {
        return zugangsdatenService.save(zugangsdaten);
    }

    @PutMapping("/{id}")
    public Zugangsdaten updateZugangsdaten(@PathVariable Long id, @RequestBody Zugangsdaten zugangsdaten) {
        return zugangsdatenService.update(id, zugangsdaten);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZugangsdaten(@PathVariable Long id) {
        zugangsdatenService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
