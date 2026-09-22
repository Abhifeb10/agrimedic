package com.agrimedic.agrimedic_backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.agrimedic.agrimedic_backend.dto.AuthResponse;
import com.agrimedic.agrimedic_backend.dto.LoginRequest;
import com.agrimedic.agrimedic_backend.dto.RegisterRequest;
import com.agrimedic.agrimedic_backend.entity.User;
import com.agrimedic.agrimedic_backend.service.UserService;
import com.agrimedic.agrimedic_backend.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userService.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User u = new User(req.getName(), req.getEmail(), req.getPassword(), req.getPhone(), req.getRole());
        User saved = userService.register(u);
        // Don't return password
        saved.setPassword(null);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var opt = userService.findByEmail(req.getEmail());
        if (opt.isEmpty()) return ResponseEntity.status(401).body("Invalid credentials");
        User u = opt.get();
        if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(u.getEmail(), u.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = userService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok("User deleted successfully");
    }

}
