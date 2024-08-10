package com.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.model.Employee;


@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
