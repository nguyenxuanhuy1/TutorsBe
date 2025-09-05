package com.example.demo.repository;

import com.example.demo.entity.ClassRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassRequestRepository extends JpaRepository<ClassRequest, Long>, JpaSpecificationExecutor<ClassRequest> {
}
