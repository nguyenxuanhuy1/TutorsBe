package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String studentName;

    @Column(length = 50, nullable = false)
    private String subject;

    @Column(length = 20, nullable = false)
    private String grade;

    @Column(length = 200)
    private String schedule;

    @Column(length = 100)
    private String currentAcademicLevel;

    @Column(length = 150)
    private String desiredGoal;

    @Column(length = 10)
    private String gender;

    @Column(length = 255)
    private String address;

    @Column(length = 200)
    private String requirements;

    @Column(length = 500)
    private String specificRequirements;

    @Column(length = 20, nullable = false)
    private String status; // PENDING, APPROVED, REJECTED


    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
