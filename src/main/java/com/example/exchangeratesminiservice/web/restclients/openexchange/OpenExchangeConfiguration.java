package com.example.exchangeratesminiservice.web.restclients.openexchange;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;



@SpringBootConfiguration
public class OpenExchangeConfiguration {

    @Value("${open-exchange.appId}")
    private String appId;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "Token ".concat(appId));
    }
}
