package com.example.exchangeratesminiservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeRatesMiniServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesMiniServiceApplication.class, args);
    }
}
