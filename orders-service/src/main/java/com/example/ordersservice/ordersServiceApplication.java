package com.example.ordersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class ordersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ordersServiceApplication.class, args);
    }

}
