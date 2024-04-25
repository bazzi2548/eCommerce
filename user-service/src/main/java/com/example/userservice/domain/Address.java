package com.example.userservice.domain;

import com.example.userservice.dto.request.SaveUserRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String addressMain;

    private String addressSub;

    @Column(nullable = false)
    private String zipcode;

    protected Address() {

    }

    public Address(SaveUserRequest request) {
        this.addressMain = request.getAddressMain();
        this.addressSub = request.getAddressSub();
        this.zipcode = request.getZipcode();
    }

    public void setUser(User user) {
        this.user = user;
        if (!user.getAddresses().contains(this)) {
            user.getAddresses().add(this);
        }
    }
}
