package com.example.demo.service;

import com.example.demo.dto.ClassRequestDTO;
import com.example.demo.dto.ClassRequestSearchDTO;
import com.example.demo.entity.ClassRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassRequestService {
    ClassRequest createRequest(ClassRequestDTO dto);
    Page<ClassRequest> getAllRequests(ClassRequestSearchDTO dto, Pageable pageable);
    List<ClassRequest> getApprovedRequests();
    ClassRequest approveRequest(Long id);
    ClassRequest rejectRequest(Long id);
}
