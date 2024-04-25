package com.example.goodsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class goodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(goodsServiceApplication.class, args);
    }

}
