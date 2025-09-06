package com.example.demo.dto;

import lombok.Data;

@Data
public class ClassRequestSearchDTO {
    private String subject;
    private String provinceId;
    private String wardId;
    private String currentAcademicLevel;
    private String desiredGoal;
    private String gender;
    private Integer page;
    private Integer size;
}
