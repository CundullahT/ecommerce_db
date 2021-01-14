package com.ecommerce_db.services;

import com.ecommerce_db.enums.OrderStatus;
import com.ecommerce_db.model.Order;
import com.ecommerce_db.model.User;
import com.ecommerce_db.repository.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void update(Order order) throws Exception {

        Order foundedOrder = orderRepository.findById(order.getId()).orElseThrow(() -> new Exception("There Is No Such Order."));
        order.setId(foundedOrder.getId());

        orderRepository.save(order);

    }

    public List<Order> readAll(){
        return orderRepository.findAll(Sort.by("id"));
    }

    public Order readById(Integer id){
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> readAllByUser(User user){
        return orderRepository.findByUser(user);
    }

    public List<Order> readAllByStatus(OrderStatus status){
        return orderRepository.findByStatus(status);
    }

    public List<Order> readAllByUserAndStatus(User user, OrderStatus status){
        return orderRepository.findByUserAndStatus(user, status);
    }

//    public void deleteById(Integer id) throws Exception {
//
//        Order foundedOrder = orderRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such Order."));
//
//        foundedOrder.setIsDeleted(true);
//        orderRepository.save(foundedOrder);
//
//    }

}
