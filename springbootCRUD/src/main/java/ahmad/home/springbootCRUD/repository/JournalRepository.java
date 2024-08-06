package ahmad.home.springbootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ahmad.home.springbootCRUD.model.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
	
}