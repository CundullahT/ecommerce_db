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
@Table(name = "orders")
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private ShipBill shipping;

    @ManyToOne
    @JoinColumn(name = "billing_id")
    private ShipBill billing;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

}

//TODO orderDate.
