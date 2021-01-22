package com.ecommerce_db.model;

import com.ecommerce_db.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Integer quantity;
    private BigDecimal price;

    private String productHistory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}

//TODO productHistory, price.
