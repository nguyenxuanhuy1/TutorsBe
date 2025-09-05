package com.example.demo.repository;

import com.example.demo.entity.DiaChinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaChinhRepository extends JpaRepository<DiaChinh, Long> {
    List<DiaChinh> findByCapDiaChinh(String capDiaChinh);
    List<DiaChinh> findByCapDiaChinhAndDiaChinhChaId(String capDiaChinh, Long diaChinhChaId);
}
