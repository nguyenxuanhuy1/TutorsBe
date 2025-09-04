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
    private String address;
    private String requirements;
    private String specificRequirements;
    @NotBlank(message = "captchaToken is required")
    private String captchaToken;
    private LocalDateTime createdAt;
}
