package home.ahmad.restapiundjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.ahmad.restapiundjpa.model.Zugangsdaten;

public interface ZugangsdatenRepository extends JpaRepository<Zugangsdaten, Long> {
    Optional<Zugangsdaten> findByEmail(String email);
}

