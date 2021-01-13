package com.ecommerce_db.enums;

public enum ProductStatus {

    ACTIVE("Active"), PENDING("Pending"), SUSPENDED("Suspended");

    private final String value;

    private ProductStatus(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
