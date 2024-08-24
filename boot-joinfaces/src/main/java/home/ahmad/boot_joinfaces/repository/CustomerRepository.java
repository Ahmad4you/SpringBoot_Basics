package home.ahmad.boot_joinfaces.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import home.ahmad.boot_joinfaces.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders WHERE c.id = :id")
    Customer findByIdWithOrders(@Param("id") Long id);
}

