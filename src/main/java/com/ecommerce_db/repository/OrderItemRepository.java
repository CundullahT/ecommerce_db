package com.ecommerce_db.repository;

import com.ecommerce_db.enums.OrderItemStatus;
import com.ecommerce_db.enums.OrderStatus;
import com.ecommerce_db.model.Order;
import com.ecommerce_db.model.OrderItem;
import com.ecommerce_db.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByStatus(OrderItemStatus status);
    List<OrderItem> findByOrder(Order order);
    List<OrderItem> findByOrderAndStatus(Order order, OrderItemStatus status);
    Optional<OrderItem> findByProduct(Product product);
    Optional<OrderItem> findAllByProductIdAndOrderUserIdAndOrderStatus(Integer productId, Integer userId, OrderStatus Status);

}
