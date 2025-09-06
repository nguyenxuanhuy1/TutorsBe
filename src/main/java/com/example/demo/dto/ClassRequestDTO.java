package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassRequestDTO {
    private String studentName;
    private String subject;
    private String grade;
    private String schedule;
    private String currentAcademicLevel;
    private String desiredGoal;
    private String gender;
    private String addressDetail;
    private String specificRequirements;
    private String status;
    private Long provinceId;
    private Long wardId;
    private String linkMaps;
    @NotBlank(message = "captchaToken is required")
    private String captchaToken;
    private LocalDateTime createdAt;
}
