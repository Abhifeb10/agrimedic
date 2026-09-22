package com.agrimedic.agrimedic_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("service", "Agri-Medic Smart Farming Assistance Backend");
        response.put("version", "0.0.1-SNAPSHOT");
        response.put("timestamp", LocalDateTime.now().toString());

        Map<String, String> demoAccounts = new LinkedHashMap<>();
        demoAccounts.put("Admin", "admin@example.com / admin123");
        demoAccounts.put("Agricultural Expert", "patel@example.com / expert123");
        demoAccounts.put("Shop Owner", "mahesh@example.com / shop123");
        demoAccounts.put("Farmer 1", "ramesh@example.com / pass123");
        demoAccounts.put("Farmer 2", "sita@example.com / pass123");
        response.put("demoAccounts", demoAccounts);

        Map<String, String> keyEndpoints = new LinkedHashMap<>();
        keyEndpoints.put("Auth Login", "POST /api/auth/login");
        keyEndpoints.put("Auth Register", "POST /api/auth/register");
        keyEndpoints.put("Fertilizers Catalog", "GET /api/fertilizers");
        keyEndpoints.put("Crop Issues", "GET /api/issues");
        keyEndpoints.put("Farmer Issues", "GET /api/issues/mine");
        keyEndpoints.put("Place Order", "POST /api/orders");
        keyEndpoints.put("Shop Products", "GET /api/shop/fertilizers");
        keyEndpoints.put("Health Check", "GET /api/health");
        response.put("endpoints", keyEndpoints);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new LinkedHashMap<>();
        status.put("status", "UP");
        status.put("database", "Connected (MySQL)");
        status.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(status);
    }
}
