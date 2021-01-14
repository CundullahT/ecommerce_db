package com.ecommerce_db.services;

import com.ecommerce_db.model.OrderItem;
import com.ecommerce_db.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem create(OrderItem orderItem) throws Exception {

        Optional<OrderItem> foundedOrderItem = orderItemRepository.findById(orderItem.getId());
        if(foundedOrderItem.isPresent()) throw new Exception("This Order Item Already Exists.");

        return orderItemRepository.save(orderItem);

    }

    public void update(OrderItem orderItem) throws Exception {

        OrderItem foundedOrderItem = orderItemRepository.findById(orderItem.getId()).orElseThrow(() -> new Exception("There Is No Such Order Item."));

        orderItem.setId(foundedOrderItem.getId());
        orderItemRepository.save(orderItem);

    }

}
