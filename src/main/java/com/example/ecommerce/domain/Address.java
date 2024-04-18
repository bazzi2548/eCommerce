package com.example.ecommerce.domain;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String address_main;

    private String address_sub;

    @Column(nullable = false)
    private String zipcode;

    protected Address() {

    }

}
