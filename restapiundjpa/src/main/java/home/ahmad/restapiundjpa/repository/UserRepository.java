package home.ahmad.restapiundjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import home.ahmad.restapiundjpa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Weitere benutzerdefinierte Abfragen
	
	
	
}
