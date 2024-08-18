package home.ahmad.restHALbrowser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.ahmad.restHALbrowser.model.Passport;
import home.ahmad.restHALbrowser.repository.PassportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    @Autowired
    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }


    @Transactional(readOnly = true)
    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Passport> findById(Long id) {
        return passportRepository.findById(id);
    }

    @Transactional
    public Passport save(Passport passport) {
        // Validierungen
        return passportRepository.save(passport);
    }

    @Transactional
    public void deleteById(Long id) {
        passportRepository.deleteById(id);
    }

    @Transactional
    public Passport update(Long id, Passport newPassport) {
        return passportRepository.findById(id)
                .map(existingPassport -> {
                    existingPassport.setPassportNo(newPassport.getPassportNo());
                    existingPassport.setIssueDate(newPassport.getIssueDate());
                    existingPassport.setExpiryDate(newPassport.getExpiryDate());
                    existingPassport.setCountryOfIssue(newPassport.getCountryOfIssue());
                    return passportRepository.save(existingPassport);
                })
                .orElseGet(() -> {
                    newPassport.setId(id);
                    return passportRepository.save(newPassport);
                });
    }
}
