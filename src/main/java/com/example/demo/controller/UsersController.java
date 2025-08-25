package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public UserEntity getUserInfo(@AuthenticationPrincipal org.springframework.security.oauth2.core.user.OAuth2User principal) {
        String email = principal.getAttribute("email");
        return userService.getUserInfo(email);
    }
}
