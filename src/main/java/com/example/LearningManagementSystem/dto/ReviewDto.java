package com.example.LearningManagementSystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private Long reviewId;
    private Long userId;
    private Long courseId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
