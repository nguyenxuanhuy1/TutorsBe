package com.example.demo.service.Impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthService(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    public String refreshAccessToken(String refreshToken) {
        // Lấy email từ refresh token
        String email = jwtUtil.extractEmail(refreshToken);

        // Check refresh token hết hạn chưa
        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token has expired, please login again.");
        }

        // Lấy thông tin user từ DB
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Sinh access token mới
        return jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
    }
}
