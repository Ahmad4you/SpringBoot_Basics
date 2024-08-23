package home.ahmad.boot_cloud01.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.ahmad.boot_cloud01.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
