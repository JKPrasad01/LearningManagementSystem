package com.example.LearningManagementSystem.apiclient;


import com.example.LearningManagementSystem.config.RestTemplateConfig;
import com.example.LearningManagementSystem.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class PaymentClient {
    private final RestTemplate restTemplate;


    public PaymentResponse getPaymentDetails(Long paymentId){

        String url ="http://localhost:8082/api/payments/"+paymentId;

        ResponseEntity<PaymentResponse> response = restTemplate.getForEntity(url, PaymentResponse.class);

        return response.getBody();
    }
}
