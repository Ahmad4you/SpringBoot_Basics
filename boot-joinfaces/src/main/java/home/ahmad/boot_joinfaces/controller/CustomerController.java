package home.ahmad.boot_joinfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import home.ahmad.boot_joinfaces.model.Customer;
import home.ahmad.boot_joinfaces.model.Order;
import home.ahmad.boot_joinfaces.repository.CustomerRepository;
import home.ahmad.boot_joinfaces.repository.OrderRepository;

import java.util.List;

/**
 * 
 * @author Ahmad Alrefai
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setOrders(customerDetails.getOrders());
                    Customer updatedCustomer = customerRepository.save(customer);
                    return ResponseEntity.ok().body(updatedCustomer);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orders);
        }
    }


    @PostMapping("/{customerId}/orders")
    public Order createOrder(@PathVariable Long customerId, @RequestBody Order order) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    order.setCustomer(customer);
                    return orderRepository.save(order);
                }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
