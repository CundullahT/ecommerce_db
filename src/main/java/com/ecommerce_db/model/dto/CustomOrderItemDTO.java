package com.ecommerce_db.model.dto;

import com.ecommerce_db.model.OrderItem;
import com.ecommerce_db.model.ShipBill;
import lombok.Data;

import java.util.List;

@Data
public class CustomOrderItemDTO {

    private List<OrderItem> orderItems;
    private ShipBill shipping;
    private ShipBill billing;

}
