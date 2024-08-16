package home.ahmad.restapiundjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.ahmad.restapiundjpa.model.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    Optional<Passport> findByPassportNo(String passportNo);
}
