package home.ahmad.spring_security_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.ahmad.spring_security_thymeleaf.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
