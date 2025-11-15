package com.example.LearningManagementSystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class ModulesDTO {
    private Long moduleId;

    @NotBlank(message = "module title is mandatory")
    private String title;
    private Integer orderNumber;
    @Valid
    private List<LessonDto> lessons;
}
