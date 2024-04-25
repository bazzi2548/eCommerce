package com.example.userservice.dto.request;

import lombok.Getter;

@Getter
public class SaveUserRequest {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    private String addressMain;
    private String addressSub;
    private String zipcode;
}
