package com.example.demo.security;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class CaptchaResponse {
    private boolean success;

    @JsonAlias("error-codes")
    private List<String> errorCodes;

    private Float score;
    private String action;
}
