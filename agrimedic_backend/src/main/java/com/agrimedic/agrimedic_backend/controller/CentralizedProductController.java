package com.agrimedic.agrimedic_backend.controller;


import com.agrimedic.agrimedic_backend.dto.*;
import com.agrimedic.agrimedic_backend.entity.*;
import com.agrimedic.agrimedic_backend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fertilizers")
public class CentralizedProductController {

    private final CentralizedProductService fertilizerService;
    private final UserService userService;

    public CentralizedProductController(CentralizedProductService fertilizerService, UserService userService) {
        this.fertilizerService = fertilizerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CentralizedProductDTO dto) {
        CentralizedProduct f = new CentralizedProduct();
        f.setName(dto.getName());
        f.setBrand(dto.getBrand());
        f.setPrice(dto.getPrice());
        f.setStock(dto.getStock());
        if (dto.getShopOwnerId() != null) {
            User shopOwner = userService.findById(dto.getShopOwnerId()).orElseThrow();
            f.setShopOwner(shopOwner);
        }
        CentralizedProduct saved = fertilizerService.create(f);
        dto.setId(saved.getId());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<CentralizedProductDTO> all() {
        return fertilizerService.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var opt = fertilizerService.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(opt.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CentralizedProductDTO dto) {
    	CentralizedProduct f = new CentralizedProduct();
        f.setName(dto.getName());
        f.setBrand(dto.getBrand());
        f.setPrice(dto.getPrice());
        f.setStock(dto.getStock());
        CentralizedProduct updated = fertilizerService.update(id, f);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        fertilizerService.delete(id);
        return ResponseEntity.ok().build();
    }

    private CentralizedProductDTO toDto(CentralizedProduct f) {
        CentralizedProductDTO d = new CentralizedProductDTO();
        d.setId(f.getId());
        d.setName(f.getName());
        d.setBrand(f.getBrand());
        d.setPrice(f.getPrice());
        d.setStock(f.getStock());
        if (f.getShopOwner() != null) d.setShopOwnerId(f.getShopOwner().getId());
        return d;
    }
}
