package com.example.LearningManagementSystem.apiclient;


import com.example.LearningManagementSystem.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "payment-service",
        url = "http://localhost:8082")
public interface FeignClientPayment {


    @GetMapping("/api/payments/{paymentId}")
    PaymentResponse getPaymentById(@PathVariable Long paymentId) throws Exception;


    @GetMapping("/api/payments")
    List<PaymentResponse> fetchAll();
}
