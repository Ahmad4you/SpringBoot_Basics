package home.ahmad.boot_joinfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import home.ahmad.boot_joinfaces.model.Order;
import home.ahmad.boot_joinfaces.repository.OrderRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }
}
