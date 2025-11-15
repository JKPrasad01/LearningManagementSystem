package com.example.LearningManagementSystem.dto;


import lombok.Data;

@Data
public class PaymentResponse {
    private Long paymentId;
    private String paymentAmount;
    private String paymentReference;

}
