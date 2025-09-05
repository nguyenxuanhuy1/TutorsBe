package com.example.demo.controller;
import com.example.demo.dto.DiaChinhDTO;
import com.example.demo.service.Impl.DiaChinhServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dia-chinh")
public class DiaChinhController {
    private final DiaChinhServiceImpl service;

    public DiaChinhController(DiaChinhServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/tinh")
    public List<DiaChinhDTO> getTinh() {
        return service.getTinh();
    }

    @GetMapping("/phuong/{idTinh}")
    public List<DiaChinhDTO> getPhuong(@PathVariable Long idTinh) {
        return service.getPhuongByTinh(idTinh);
    }
}
