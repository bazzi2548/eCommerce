package com.example.ordersservice.dto.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private String phoneNumber;
    private String password;

}
