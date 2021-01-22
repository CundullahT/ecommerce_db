package com.ecommerce_db.model;

import com.ecommerce_db.enums.ShipBillStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shipment_billing")
public class ShipBill extends BaseEntity {

    private String street1;
    private String street2;
    private String country;
    private String state;
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ShipBillStatus status;

}
