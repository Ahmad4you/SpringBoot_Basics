package home.ahmad.restapi_jersesy.service;



import org.springframework.stereotype.Service;

import home.ahmad.restapi_jersey.model.Employee;
import home.ahmad.restapi_jersey.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
	
//	 private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
//
//	    public EmployeeService() {
//	        this.repository = null;
//			logger.info("EmployeeService bean created");
//	    }

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
