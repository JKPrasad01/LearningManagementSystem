package com.example.LearningManagementSystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EnrollmentDto {
    private Long enrollmentId;
    private Long userId;
    private Long courseId;
    private LocalDateTime enrolledAt;
    private Integer progress;   // percentage
}
