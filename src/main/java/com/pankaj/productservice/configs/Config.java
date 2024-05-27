package com.pankaj.productservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    @LoadBalanced//This will make restTemplate load balanced for eureka.
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}
