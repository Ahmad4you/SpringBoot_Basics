package home.ahmad.boot_joinfaces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import home.ahmad.boot_joinfaces.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	// Benutzerdefinierte Methode zum Abrufen der Bestellungen nach customerId
    List<Order> findByCustomerId(Long customerId);
}
