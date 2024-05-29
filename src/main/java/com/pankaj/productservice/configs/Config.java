package com.pankaj.productservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    @LoadBalanced//This will make restTemplate load balanced for eureka. Thi is a client side load balancing used for
    // inter service communication.
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}
