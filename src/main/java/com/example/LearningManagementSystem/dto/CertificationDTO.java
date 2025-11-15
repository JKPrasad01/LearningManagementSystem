package com.example.LearningManagementSystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CertificationDTO {
    private Long certificationId;
    private Long userId;
    private Long courseId;
    private LocalDateTime issuedDate;
    private String certificationURL;
}
