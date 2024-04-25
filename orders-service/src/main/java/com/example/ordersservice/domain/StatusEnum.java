package com.example.ordersservice.domain;

public enum StatusEnum {
    주문완료(0),
    배송중(1),
    배송완료(2),
    취소완료(3),
    반품중(4),
    반품완료(5);

    private final Integer value;

    StatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
