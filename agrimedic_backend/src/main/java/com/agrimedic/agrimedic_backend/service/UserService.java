package com.agrimedic.agrimedic_backend.service;

import java.util.List;
import java.util.Optional;

import com.agrimedic.agrimedic_backend.entity.User;

public interface UserService {
    User register(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    List<User> findAll();
    boolean deleteById(Long id);
  
}