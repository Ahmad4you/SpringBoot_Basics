package com.home.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.home.model.Employee;
import com.home.service.EmployeeService;

import java.util.Date;
import java.util.List;
/**
 * 
 * @author Ahmad Alrefai
 */
@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String index() {
        return "Aktuelle Date : " + new Date();
    }

    @RequestMapping(path = "/employee", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(value = "/error-test", method = RequestMethod.GET)
    public Employee error() throws Exception {
        throw new Exception();
    }
}