package com.home.service;


import java.util.List;

import com.home.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	
}
