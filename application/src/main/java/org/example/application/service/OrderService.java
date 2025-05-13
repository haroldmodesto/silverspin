package org.example.application.service;

import org.example.application.entity.Order;
import org.example.application.entity.ShippingStatus;
import org.example.application.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        if (order.getId() == null) {
            order.setStatus(ShippingStatus.NONE.name());
        }
        orderRepository.save(order);
    }

    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }
}
