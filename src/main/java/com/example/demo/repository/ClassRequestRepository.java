package com.example.demo.repository;

import com.example.demo.entity.ClassRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRequestRepository extends JpaRepository<ClassRequest, Long> {
    List<ClassRequest> findByStatus(String status);
}
