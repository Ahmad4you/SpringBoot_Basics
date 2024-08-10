package com.home.view;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.home.model.Employee;
import com.home.service.EmployeeService;

import jakarta.enterprise.context.RequestScoped;


/**
 * 
 * @author Ahmad Alrefai
 */
@RequestScoped
@Controller
public class EmployeeView{
		
	@Autowired
    private EmployeeService employeeService;


    public List<Employee> getEmployeesList() {
        return employeeService.getAllEmployees();
    }

}