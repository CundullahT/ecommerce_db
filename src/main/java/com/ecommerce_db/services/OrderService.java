package com.ecommerce_db.services;

import com.ecommerce_db.model.Order;
import com.ecommerce_db.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) throws Exception {

        Optional<Order> foundedOrder = orderRepository.findById(order.getId());
        if (foundedOrder.isPresent()) throw new Exception("This Order Already Exists.");

        return orderRepository.save(order);

    }

}
