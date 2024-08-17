package home.ahmad.boot_jpa_jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.ahmad.boot_jpa_jdbc.model.Zugangsdaten;
import home.ahmad.boot_jpa_jdbc.repository.ZugangsdatenRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ZugangsdatenService {

    private final ZugangsdatenRepository zugangsdatenRepository;

    @Autowired
    public ZugangsdatenService(ZugangsdatenRepository zugangsdatenRepository) {
        this.zugangsdatenRepository = zugangsdatenRepository;
    }

    
    @Transactional(readOnly = true)
    public List<Zugangsdaten> findAll() {
        return zugangsdatenRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Zugangsdaten> findById(Long id) {
        return zugangsdatenRepository.findById(id);
    }

    @Transactional
    public Zugangsdaten save(Zugangsdaten zugangsdaten) {
        // Passwort-Hashing
        return zugangsdatenRepository.save(zugangsdaten);
    }

    @Transactional
    public void deleteById(Long id) {
        zugangsdatenRepository.deleteById(id);
    }

    @Transactional
    public Zugangsdaten update(Long id, Zugangsdaten newZugangsdaten) {
        return zugangsdatenRepository.findById(id)
                .map(existingZugangsdaten -> {
                    existingZugangsdaten.setEmail(newZugangsdaten.getEmail());
                    existingZugangsdaten.setPhoneNumber(newZugangsdaten.getPhoneNumber());
                    existingZugangsdaten.setCurrentPassword(newZugangsdaten.getCurrentPassword());
//                    existingZugangsdaten.setOldPassword(newZugangsdaten.getOldPassword());
//                    existingZugangsdaten.setLastChanged(LocalDateTime.now());
                    return zugangsdatenRepository.save(existingZugangsdaten);
                })
                .orElseGet(() -> {
                    newZugangsdaten.setId(id);
                    return zugangsdatenRepository.save(newZugangsdaten);
                });
    }
}

