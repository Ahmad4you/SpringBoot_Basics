package home.ahmad.boot_joinfaces;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import home.ahmad.boot_joinfaces.model.Customer;
import home.ahmad.boot_joinfaces.model.Order;
import home.ahmad.boot_joinfaces.repository.CustomerRepository;
import home.ahmad.boot_joinfaces.repository.OrderRepository;

/**
 * GET http://localhost:8080/api/customers
 * GET http://localhost:8080/api/customers/1/orders
 * 
 * @author Ahmad Alrefai
 */
@SpringBootApplication
public class BootJoinfacesApplication {

	 private static final Logger log = LoggerFactory.getLogger(BootJoinfacesApplication.class);

	    public static void main(String[] args) {
	        SpringApplication.run(BootJoinfacesApplication.class, args);
	        
	    }
	    
	    
	    @Bean
	    public CommandLineRunner demo(CustomerRepository repository, OrderRepository orderRepository) {
	        return (args) -> {
	            // Create orders
	            Order order1 = new Order();
	            order1.setProduct("Produkt1");
	            order1.setQuantity(5);

	            Order order2 = new Order();
	            order2.setProduct("Produkt2");
	            order2.setQuantity(10);

	            // Create customer
	            Customer customer = new Customer();
	            customer.setName("Ahmad");
	            customer.setOrders(List.of(order1, order2));

	            // Set the customer reference in orders
	            order1.setCustomer(customer);
	            order2.setCustomer(customer);

	            // Save customer and orders
	            repository.save(customer);

	            // Fetch all customers
	            log.info("Customers found with findAll():");
	            log.info("-------------------------------");
	            for (Customer c : repository.findAll()) {
	                log.info(c.getName() );
	                for (Order order : c.getOrders()) {
	                    log.info("Order: " + order.getProduct() + " Quantity: " + order.getQuantity());
	                }
	            }
	        };
	    }
	    
	   

	}