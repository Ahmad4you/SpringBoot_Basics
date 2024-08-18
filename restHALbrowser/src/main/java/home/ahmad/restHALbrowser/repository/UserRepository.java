package home.ahmad.restHALbrowser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import home.ahmad.restHALbrowser.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
    // Weitere benutzerdefinierte Abfragen
	
	@Query("select j from User j where j.firstName like %?1%")
    List<User> findByCustomQuery(String word);
	
	@Query("select u from User u where u.lastName = ?1")
	List<User> findByLastName(String lastName);

	@Query("select u from User u where u.age = ?1")
	List<User> findByAge(int age);
	
	// Abfrage nach Benutzern mit einem Mindestalter
	@Query("select u from User u where u.age >= ?1")
	List<User> findByAgeGreaterThanEqual(int age);
	
	@Query("select u from User u where u.firstName = ?1 and u.lastName = ?2")
	List<User> findByFirstNameAndLastName(String firstName, String lastName);
		
	@Query("select u from User u where u.firstName like %?1%")
	List<User> findByFirstNameLike(String pattern);
	
	@Query("select u from User u join u.passport p where u.age = ?1 and p.countryOfIssue = ?2")
	List<User> findByAgeAndCountryOfIssue(int age, String country);
	
	@Query("select u from User u where u.zugangsdaten.phoneNumber is null")
	List<User> findUsersWithoutPhoneNumber();
	
//	@Query("select u from User u where u.createdDate between ?1 and ?2")
//	List<User> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
	
	@Query("select u from User u where u.zugangsdaten.currentPassword = ?1")
	List<User> findByPassword(String password);
	
	// Abfrage nach Benutzern basierend auf mehreren optionalen Parametern
	@Query("select u from User u where "
		     + "(:firstName is null or u.firstName = :firstName) and "
		     + "(:lastName is null or u.lastName = :lastName) and "
		     + "(:age is null or u.age = :age)")
		List<User> findByMultipleCriteria(
		    @Param("firstName") String firstName,
		    @Param("lastName") String lastName,
		    @Param("age") Integer age);
	
	

	
}
