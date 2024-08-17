package home.ahmad.boot_jpa_jdbc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.ahmad.boot_jpa_jdbc.model.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    Optional<Passport> findByPassportNo(String passportNo);
}
