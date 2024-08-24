package home.ahmad.boot_joinfaces.controller;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import home.ahmad.boot_joinfaces.model.Customer;
import home.ahmad.boot_joinfaces.model.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@ViewScoped
public class RestCustomerBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4323838204465558207L;
	private static final Logger logger = LoggerFactory.getLogger(RestCustomerBean.class);
	private List<Customer> customers;
    private Customer selectedCustomer;
    private Customer newCustomer = new Customer();
    private List<Order> orders;
    private Order newOrder = new Order();

    
    @Autowired // externen REST-API kommuniziert
    private RestTemplate restTemplate;

    private final String apiBaseUrl = "http://localhost:8080/api/customers";

    @PostConstruct
    public void init() {
    	customers = new ArrayList<>();
        loadCustomers();
    }

    public void loadCustomers() {
        Optional<Customer[]> optionalCustomers = Optional.ofNullable(restTemplate.getForObject(apiBaseUrl, Customer[].class));
        this.customers = new ArrayList<>(optionalCustomers.map(Arrays::asList).orElse(Collections.emptyList()));
    }


    public void onCustomerSelect() {
        if (selectedCustomer != null) {
            Long customerId = selectedCustomer.getId();
            try {
                Order[] orderArray = restTemplate.getForObject(apiBaseUrl + "/" + customerId + "/orders", Order[].class);
                if (orderArray != null) {
                    this.orders = Arrays.asList(orderArray);
                    logger.info("Orders loaded: {}", this.orders);
                } else {
                    this.orders = new ArrayList<>();
                    logger.info("No orders found for customer ID: {}", customerId);
                }
            } catch (Exception e) {
                logger.error("Error loading orders for customer ID: {}", customerId, e);
            }
        } else {
            logger.warn("No customer selected");
        }
    }


//    public void addCustomer() {
//        Customer createdCustomer = restTemplate.postForObject(apiBaseUrl, newCustomer, Customer.class);
//        if (createdCustomer != null) {
//            customers.add(createdCustomer);
//            newCustomer = new Customer();  // Reset the form
//        }
//    }
    
    public void addCustomer() {
        try {
            Customer createdCustomer = restTemplate.postForObject(apiBaseUrl, newCustomer, Customer.class);
            logger.info("Created Customer: {}", createdCustomer);

            if (createdCustomer != null) {
                if (customers.stream().noneMatch(c -> c.getId().equals(createdCustomer.getId()))) {
                    // Erstelle eine veränderliche Kopie der Liste
                    List<Customer> modifiableCustomers = new ArrayList<>(customers);
                    modifiableCustomers.add(createdCustomer);
                    customers = modifiableCustomers;
                } else {
                    logger.warn("Customer with ID {} already exists.", createdCustomer.getId());
                }
                newCustomer = new Customer();  // Reset the form
            } else {
                logger.warn("Created customer is null.");
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error while adding customer: {}", e.getStatusCode(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while adding customer", e);
        }
    }

    
    public void addOrderToCustomer() {
        if (selectedCustomer != null && newOrder != null) {
            String orderApiUrl = apiBaseUrl + "/" + selectedCustomer.getId() + "/orders";
            Order createdOrder = restTemplate.postForObject(orderApiUrl, newOrder, Order.class);
            if (createdOrder != null) {
                orders.add(createdOrder);
                newOrder = new Order(); // Formular zurücksetzen
            }
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

    public Customer getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Customer newCustomer) {
        this.newCustomer = newCustomer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

	public Order getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}
}
