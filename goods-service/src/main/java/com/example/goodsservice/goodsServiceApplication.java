package com.example.goodsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class goodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(goodsServiceApplication.class, args);
    }

}
