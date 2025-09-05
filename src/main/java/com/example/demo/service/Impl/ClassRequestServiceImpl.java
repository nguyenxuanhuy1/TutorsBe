package com.example.demo.service.Impl;

import com.example.demo.dto.ClassRequestDTO;
import com.example.demo.dto.ClassRequestSearchDTO;
import com.example.demo.entity.ClassRequest;
import com.example.demo.repository.ClassRequestRepository;
import com.example.demo.service.ClassRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
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
        return repository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.getSubject() != null && !dto.getSubject().isEmpty()) {
                predicates.add(cb.like(root.get("subject"), "%" + dto.getSubject() + "%"));
            }

            if (dto.getCurrentAcademicLevel() != null && !dto.getCurrentAcademicLevel().isEmpty()) {
                predicates.add(cb.equal(root.get("currentAcademicLevel"), dto.getCurrentAcademicLevel()));
            }

            if (dto.getAddress() != null && !dto.getAddress().isEmpty()) {
                predicates.add(cb.equal(root.get("address"), dto.getAddress()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    // user
    @Override
    public Page<ClassRequest> searchRequestsUser(ClassRequestSearchDTO dto, Pageable pageable) {
        return repository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("status"), "APPROVE"));

            if (dto.getGender() != null && !dto.getGender().isEmpty()) {
                predicates.add(cb.equal(root.get("gender"), dto.getGender()));
            }

            if (dto.getSubject() != null && !dto.getSubject().isEmpty()) {
                predicates.add(cb.like(root.get("subject"), "%" + dto.getSubject() + "%"));
            }
            if (dto.getDesiredGoal() != null && !dto.getDesiredGoal().isEmpty()) {
                predicates.add(cb.equal(root.get("desiredGoal"), dto.getDesiredGoal()));
            }

            if (dto.getCurrentAcademicLevel() != null && !dto.getCurrentAcademicLevel().isEmpty()) {
                predicates.add(cb.equal(root.get("currentAcademicLevel"), dto.getCurrentAcademicLevel()));
            }

            if (dto.getAddress() != null && !dto.getAddress().isEmpty()) {
                predicates.add(cb.equal(root.get("address"), dto.getAddress()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }


    @Override
    public void approveRequests(List<Long> ids) {
        List<ClassRequest> requests = repository.findAllById(ids);
        requests.forEach(r -> r.setStatus("APPROVED"));
        repository.saveAll(requests);
    }

    @Override
    public void rejectRequests(List<Long> ids) {
        List<ClassRequest> requests = repository.findAllById(ids);
        requests.forEach(r -> r.setStatus("REJECTED"));
        repository.saveAll(requests);
    }
}