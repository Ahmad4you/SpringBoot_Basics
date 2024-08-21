package home.ahmad.boot_actuator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import home.ahmad.boot_actuator.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}