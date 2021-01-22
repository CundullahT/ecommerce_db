package com.ecommerce_db.enums;

public enum OrderItemStatus {

    STOCKABLE("Stockable"), TRANSACTABLE("Transactable"), PURCHASABLE("Purchasable"), PAYED("Payed");

    private final String value;

    private OrderItemStatus(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
