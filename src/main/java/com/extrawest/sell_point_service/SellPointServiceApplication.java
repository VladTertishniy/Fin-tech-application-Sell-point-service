package com.extrawest.sell_point_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SellPointServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellPointServiceApplication.class, args);
    }

}
