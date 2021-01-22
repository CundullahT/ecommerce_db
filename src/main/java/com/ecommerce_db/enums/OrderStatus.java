package com.ecommerce_db.enums;

public enum OrderStatus {

    PENDING("Pending"), APPROVED("Approved"), CANCELLED("Cancelled"), SHIPPED("Shipped"),
    COMPLETED("Completed"), DECLINED("Declined"), AWAITING_PAYMENT("Awaiting Payment"), PAYED("Payed"),
    IN_PROCESS("In_Process"), READY_TO_SHIP("Ready_To_Ship"), ON_HOLD("On_Hold");

    private final String value;

    private OrderStatus(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
