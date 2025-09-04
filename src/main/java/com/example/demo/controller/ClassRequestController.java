package com.example.demo.controller;

import com.example.demo.dto.ClassRequestDTO;
import com.example.demo.dto.ClassRequestSearchDTO;
import com.example.demo.entity.ClassRequest;
import com.example.demo.service.ClassRequestService;
import com.example.demo.service.Impl.CaptchaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassRequestController {
    private final ClassRequestService classRequestService;
    private final CaptchaService captchaService;

    public ClassRequestController(ClassRequestService classRequestService, CaptchaService captchaService) {
        this.classRequestService = classRequestService;
        this.captchaService = captchaService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRequest(@Valid @RequestBody ClassRequestDTO dto) {
        boolean ok = captchaService.verify(dto.getCaptchaToken());
        if (!ok) {
            return ResponseEntity.badRequest().body("Captcha verification failed");
        }
        dto.setCaptchaToken(null);
        ClassRequest created = classRequestService.createRequest(dto);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ClassRequest>> searchRequests(@RequestBody ClassRequestSearchDTO dto) {
        int page = (dto.getPage() != null) ? dto.getPage() : 0;
        int size = (dto.getSize() != null) ? dto.getSize() : 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<ClassRequest> result = classRequestService.getAllRequests(dto, pageable);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approved")
    public ResponseEntity<List<ClassRequest>> getApprovedRequests() {
        return ResponseEntity.ok(classRequestService.getApprovedRequests());
    }

    //  Duyệt yêu cầu theo id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<ClassRequest> approveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(classRequestService.approveRequest(id));
    }

    //  Từ chối yêu cầu theo id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/reject")
    public ResponseEntity<ClassRequest> rejectRequest(@PathVariable Long id) {
        return ResponseEntity.ok(classRequestService.rejectRequest(id));
    }
}
