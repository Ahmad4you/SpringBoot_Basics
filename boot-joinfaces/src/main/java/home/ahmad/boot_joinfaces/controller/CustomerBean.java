package home.ahmad.boot_joinfaces.controller;


import home.ahmad.boot_joinfaces.model.Customer;
import home.ahmad.boot_joinfaces.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CustomerBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6066103687690096131L;

	@Inject
    private CustomerRepository customerRepository;

    private List<Customer> customers;
    private Customer selectedCustomer;
//    @Getter @Setter
    private Long customerId;

    
    @PostConstruct
    public void init() {
        customers = customerRepository.findAll();
    }

    public void saveCustomer() {
        customerRepository.save(selectedCustomer);
    }
    
    public void loadCustomerWithOrders() {
    	 if (selectedCustomer != null) {
             this.customerId = selectedCustomer.getId(); // Setze die customerId
             System.out.println("Customer ID: " + customerId);
             // Logik zum Laden der Bestellungen für den Kunden
             Customer customer = customerRepository.findByIdWithOrders(customerId);
             if (customer != null) {
            	 customer.getOrders().size(); // Zum Laden der Bestellungen
             }
         } else {
             System.out.println("Selected Customer is null");
         }
    	 
        
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
    
    
}