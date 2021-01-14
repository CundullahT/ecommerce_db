package com.ecommerce_db.services;

import com.ecommerce_db.enums.OrderStatus;
import com.ecommerce_db.model.Order;
import com.ecommerce_db.model.OrderItem;
import com.ecommerce_db.model.User;
import com.ecommerce_db.repository.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
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

    public void deleteById(Integer id) throws Exception {

        Order foundedOrder = orderRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such Order."));
        List<OrderItem> orderItems = orderItemService.readAllByOrder(foundedOrder);

        if (orderItems.size() > 0) throw new Exception("This Order Has Order Items So It Can Not Be Deleted.");

        foundedOrder.setIsDeleted(true);
        orderRepository.save(foundedOrder);

    }

}
