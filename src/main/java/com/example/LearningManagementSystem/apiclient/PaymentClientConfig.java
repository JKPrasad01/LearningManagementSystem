package com.example.LearningManagementSystem.apiclient;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentClientConfig {

    @Bean
    public ErrorDecoder myCustomErrorDecoder(){
        return new MyCustomPaymentClientErrorDecoder();
    }
}
