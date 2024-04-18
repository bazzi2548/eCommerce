package com.example.ecommerce.dto.request;

import lombok.Getter;

@Getter
public class SaveUserRequest {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;

}
