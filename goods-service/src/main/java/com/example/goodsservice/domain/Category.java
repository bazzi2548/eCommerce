package com.example.goodsservice.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryId;

    @Column(nullable = false)
    private String CategoryName;

    @OneToMany(mappedBy = "category")
    private List<Goods> goodsList = new ArrayList<>();

    protected Category() {}

}
