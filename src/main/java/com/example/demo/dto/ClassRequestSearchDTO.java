package com.example.demo.dto;

import lombok.Data;

@Data
public class ClassRequestSearchDTO {
    private String subject;
    private String address;
    private String currentAcademicLevel;
    private Integer page;
    private Integer size;
}
