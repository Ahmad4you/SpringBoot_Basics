package home.ahmad.boot_jpa_jdbc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.ahmad.boot_jpa_jdbc.model.Zugangsdaten;



public interface ZugangsdatenRepository extends JpaRepository<Zugangsdaten, Long> {
    Optional<Zugangsdaten> findByEmail(String email);
}

