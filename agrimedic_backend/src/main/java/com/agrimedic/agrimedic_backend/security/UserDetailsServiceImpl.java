package com.agrimedic.agrimedic_backend.security;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agrimedic.agrimedic_backend.entity.User;
import com.agrimedic.agrimedic_backend.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repo;
    public UserDetailsServiceImpl(UserRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(u);
    }
}