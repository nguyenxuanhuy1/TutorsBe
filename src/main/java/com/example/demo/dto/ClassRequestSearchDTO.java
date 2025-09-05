package com.example.demo.dto;

import lombok.Data;

@Data
public class ClassRequestSearchDTO {
    private String subject;
    private String address;
    private String currentAcademicLevel;
    private String desiredGoal;
    private String gender;
    private Integer page;
    private Integer size;
}
