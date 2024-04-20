package com.example.ecommerce.dto.response;

import com.example.ecommerce.domain.User;
import lombok.Getter;

import java.util.List;

@Getter
public class MyPageResponse {
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
    private List<AddressDTO> addresses;

    public MyPageResponse(User user) {
        email = user.getEmail();
        name = user.getName();
        phoneNumber = user.getPhoneNumber();
        role = user.getRole();
        this.addresses = user.getAddresses()
                .stream()
                .map(AddressDTO::new)
                .toList();
    }
}
