package com.ecommerce_db.services;

import com.ecommerce_db.enums.OrderItemStatus;
import com.ecommerce_db.enums.OrderStatus;
import com.ecommerce_db.model.Order;
import com.ecommerce_db.model.OrderItem;
import com.ecommerce_db.model.Product;
import com.ecommerce_db.model.User;
import com.ecommerce_db.model.dto.CustomOrderItemDTO;
import com.ecommerce_db.repository.OrderItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final UserService userService;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService, UserService userService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.userService = userService;
    }

    public OrderItem create(OrderItem orderItem) throws Exception {
//TODO Get currentUser
        List<Order> orders = orderService.readAllByUserAndStatus(orderItem.getOrder().getUser(), OrderStatus.PENDING);

        if(orders.size() > 0){
            Order currentOrder = orders.get(0);
            orderItem.setOrder(currentOrder);
        }

//TODO Get currentUser
        Optional<OrderItem> foundedItem = orderItemRepository
                .findAllByProductIdAndOrderUserIdAndOrderStatus(orderItem.getOrder().getId(),
                orderItem.getOrder().getUser().getId(),
                OrderStatus.PENDING);

        if(foundedItem.isPresent()){
            foundedItem.get().setPrice(orderItem.getPrice());
            foundedItem.get().setQuantity(orderItem.getQuantity());
            return orderItemRepository.save(foundedItem.get());
        }

        return orderItemRepository.save(orderItem);

    }

    public List<OrderItem> buildOrderItems(CustomOrderItemDTO orderItemDTO){
//TODO Get currentUser
        User currentUser = userService.readByEmail("admin@admin.com");
        List<Order> orders = orderService.readAllByUserAndStatus(currentUser, OrderStatus.PENDING);
        Order currentOrder = orders.get(0);

        currentOrder.setBilling(orderItemDTO.getBilling());
        currentOrder.setShipping(orderItemDTO.getShipping());
        currentOrder.setStatus(OrderStatus.PAYED);

        List<OrderItem> orderItems =
                orderItemDTO.getOrderItems().stream().peek(orderItem -> {

                    currentOrder.setTotalPrice(currentOrder.getTotalPrice().add(orderItem.getPrice()));
                    orderItem.setStatus(OrderStatus.APPROVED);
                    orderItem.setOrder(currentOrder);

        }).collect(Collectors.toList());

        return orderItemRepository.saveAll(orderItemDTO.getOrderItems());

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
