package com.example.demo.service.Impl;

import com.example.demo.dto.DiaChinhDTO;
import com.example.demo.repository.DiaChinhRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaChinhServiceImpl {
    private final DiaChinhRepository repository;

    public DiaChinhServiceImpl(DiaChinhRepository repository) {
        this.repository = repository;
    }
    public List<DiaChinhDTO> getTinh() {
        return repository.findByCapDiaChinh("T")
                .stream()
                .map(dc -> new DiaChinhDTO(dc.getId(), dc.getTen()))
                .collect(Collectors.toList());
    }

    public List<DiaChinhDTO> getPhuongByTinh(Long idTinh) {
        return repository.findByCapDiaChinhAndDiaChinhChaId("P", idTinh)
                .stream()
                .map(dc -> new DiaChinhDTO(dc.getId(), dc.getTen()))
                .collect(Collectors.toList());
    }
}
