package com.ecommerce_db.enums;

public enum OrderStatus {

    PENDING("Pending"), APPROVED("Approved"), CANCELLED("Cancelled"), SHIPPED("Shipped"),
    COMPLETED("Completed"), DECLINED("Declined"), AWAITING_PAYMENT("Awaiting Payment");

    private final String value;

    private OrderStatus(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
