package com.example.demo.controller;

import com.example.demo.dto.ClassRequestDTO;
import com.example.demo.dto.ClassRequestSearchDTO;
import com.example.demo.dto.PageResponse;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    // search full cho admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/search/admin")
    public ResponseEntity<PageResponse<ClassRequest>> searchRequests(@RequestBody ClassRequestSearchDTO dto) {
        int page = (dto.getPage() != null) ? dto.getPage() : 0;
        int size = (dto.getSize() != null) ? dto.getSize() : 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<ClassRequest> result = classRequestService.getAllRequests(dto, pageable);

        PageResponse<ClassRequest> response = new PageResponse<>(
                result.getContent(),
                result.getTotalElements(),
                result.getSize(),
                result.getNumber()
        );

        return ResponseEntity.ok(response);
    }

// search cho người dùng

    @PostMapping("/search/user")
    public ResponseEntity<PageResponse<ClassRequest>> searchRequestsUser(@RequestBody ClassRequestSearchDTO dto) {
        int page = (dto.getPage() != null) ? dto.getPage() : 0;
        int size = (dto.getSize() != null) ? dto.getSize() : 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<ClassRequest> result = classRequestService.searchRequestsUser(dto, pageable);

        PageResponse<ClassRequest> response = new PageResponse<>(
                result.getContent(),
                result.getTotalElements(),
                result.getSize(),
                result.getNumber()
        );

        return ResponseEntity.ok(response);
    }


    //  Duyệt yêu cầu theo [ids]
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/approve")
    public ResponseEntity<Map<String, String>> approveRequests(@RequestBody List<Long> ids) {
        classRequestService.approveRequests(ids);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Phê duyệt thành công " + ids.size() + " yêu cầu.");
        return ResponseEntity.ok(response);
    }

    //  Từ chối yêu cầu theo ids
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reject")
    public ResponseEntity<Map<String, String>> rejectRequests(@RequestBody List<Long> ids) {
        classRequestService.rejectRequests(ids);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Từ chối thành công " + ids.size() + " yêu cầu.");
        return ResponseEntity.ok(response);
    }
}
