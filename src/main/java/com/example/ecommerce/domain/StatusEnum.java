package com.example.ecommerce.domain;

public enum StatusEnum {
    주문완료(0),
    배송중(1),
    배송완료(2),
    주문취소(3);

    private final Integer value;

    StatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
