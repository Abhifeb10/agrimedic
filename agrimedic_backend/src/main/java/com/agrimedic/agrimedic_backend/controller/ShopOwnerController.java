package com.agrimedic.agrimedic_backend.controller;

import com.agrimedic.agrimedic_backend.entity.CentralizedProduct;
import com.agrimedic.agrimedic_backend.service.CentralizedProductService;
import com.agrimedic.agrimedic_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopOwnerController {

    private final CentralizedProductService fertilizerService;
    private final UserService userService;

    public ShopOwnerController(CentralizedProductService fertilizerService, UserService userService) {
        this.fertilizerService = fertilizerService;
        this.userService = userService;
    }

    // List all fertilizers linked to this shop owner
    @GetMapping("/fertilizers")
    public ResponseEntity<List<CentralizedProduct>> getFertilizers(Authentication auth) {
        String email = auth.getName();
        var shopOwner = userService.findByEmail(email).orElseThrow();
        List<CentralizedProduct> list = fertilizerService.findByShopOwnerId(shopOwner.getId());
        return ResponseEntity.ok(list);
    }
}
