package com.ecommerce_db.services;

import com.ecommerce_db.enums.OrderItemStatus;
import com.ecommerce_db.enums.OrderStatus;
import com.ecommerce_db.model.Order;
import com.ecommerce_db.model.OrderItem;
import com.ecommerce_db.model.Product;
import com.ecommerce_db.repository.OrderItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
    }

    public OrderItem create(OrderItem orderItem) throws Exception {

        List<Order> orders = orderService.readAllByUserAndStatus(orderItem.getOrder().getUser(), OrderStatus.PENDING);

        if(orders.size() > 0){
            Order currentOrder = orders.get(0);
            orderItem.setOrder(currentOrder);
        }

//        {
//            product: {id: 1},
//            price: 20.25,
//            quantity: 2,
//            order: {user: {id: 1}}
//        }

        Optional<OrderItem> foundedOrderItem = orderItemRepository.findById(orderItem.getId());
        if(foundedOrderItem.isPresent()) throw new Exception("This Order Item Already Exists.");

        return orderItemRepository.save(orderItem);

    }

    public void update(OrderItem orderItem) throws Exception {

        OrderItem foundedOrderItem = orderItemRepository.findById(orderItem.getId()).orElseThrow(() -> new Exception("There Is No Such Order Item."));

        orderItem.setId(foundedOrderItem.getId());
        orderItemRepository.save(orderItem);

    }

    public List<OrderItem> readAll(){
        return orderItemRepository.findAll(Sort.by("id"));
    }

    public OrderItem readById(Integer id){
        return orderItemRepository.findById(id).orElse(null);
    }

    public List<OrderItem> readAllByOrder(Order order){
        return orderItemRepository.findByOrder(order);
    }

    public List<OrderItem> readAllByStatus(OrderItemStatus status){
        return orderItemRepository.findByStatus(status);
    }

    public OrderItem readByProduct(Product product){
        return orderItemRepository.findByProduct(product).orElse(null);
    }

    public List<OrderItem> readAllByOrderAndStatus(Order order, OrderItemStatus status){
        return orderItemRepository.findByOrderAndStatus(order,status);
    }

//    public void deleteById(Integer id) throws Exception {
//
//        OrderItem foundedOrderItem = orderItemRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such Order Item."));
//
//        foundedOrderItem.setIsDeleted(true);
//        orderItemRepository.save(foundedOrderItem);
//
//    }

}
