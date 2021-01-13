package com.ecommerce_db.enums;

public enum UserStatus {

    ACTIVE("Active"), PENDING("Pending"), SUSPENDED("Suspended");

    private final String value;

    private UserStatus(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
