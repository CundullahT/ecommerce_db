package com.ecommerce_db.repository;

import com.ecommerce_db.enums.OrderStatus;
import com.ecommerce_db.model.Order;
import com.ecommerce_db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByUserAndStatus(User user, OrderStatus status);

}
