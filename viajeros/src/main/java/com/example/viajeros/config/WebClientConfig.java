package com.example.viajeros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient vehiculoWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083/api/vehiculos")
                .build();
    }
}

