package com.example.demo.service.Impl;

import com.example.demo.dto.ClassRequestDTO;
import com.example.demo.dto.ClassRequestSearchDTO;
import com.example.demo.entity.ClassRequest;
import com.example.demo.repository.ClassRequestRepository;
import com.example.demo.service.ClassRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRequestServiceImpl implements ClassRequestService {
    private final ClassRequestRepository repository;

    public ClassRequestServiceImpl(ClassRequestRepository repository) {
        this.repository = repository;
    }
    @Override
    public ClassRequest createRequest(ClassRequestDTO dto) {
        ClassRequest request = ClassRequest.builder()
                .studentName(dto.getStudentName())
                .subject(dto.getSubject())
                .grade(dto.getGrade())
                .schedule(dto.getSchedule())
                .currentAcademicLevel(dto.getCurrentAcademicLevel())
                .desiredGoal(dto.getDesiredGoal())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .requirements(dto.getRequirements())
                .specificRequirements(dto.getSpecificRequirements())
                .status("PENDING") // mặc định
                .build();

        return repository.save(request);
    }

    @Override
    public Page<ClassRequest> getAllRequests(ClassRequestSearchDTO dto, Pageable pageable) {
        // Nếu muốn lọc theo DTO, có thể dùng Specification hoặc Query
        // Ví dụ tạm thời trả tất cả:
        return repository.findAll(pageable);
    }
    @Override
    public List<ClassRequest> getApprovedRequests() {
        return repository.findByStatus("APPROVED");
    }

    @Override
    public ClassRequest approveRequest(Long id) {
        ClassRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("APPROVED");
        return repository.save(request);
    }

    @Override
    public ClassRequest rejectRequest(Long id) {
        ClassRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("REJECTED");
        return repository.save(request);
    }
}
