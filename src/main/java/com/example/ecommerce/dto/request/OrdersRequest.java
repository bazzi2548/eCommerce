package com.example.ecommerce.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrdersRequest {

    List<Long> wishlists = new ArrayList<>();

}
