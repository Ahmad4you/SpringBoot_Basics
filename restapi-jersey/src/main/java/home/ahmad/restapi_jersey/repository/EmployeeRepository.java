package home.ahmad.restapi_jersey.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import home.ahmad.restapi_jersey.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
