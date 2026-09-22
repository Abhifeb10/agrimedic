package com.agrimedic.agrimedic_backend.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrimedic.agrimedic_backend.entity.User;
import com.agrimedic.agrimedic_backend.repository.UserRepository;
import com.agrimedic.agrimedic_backend.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public User register(User user) {
		// hash password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null)
			return false;
		userRepository.deleteById(id);
		return true;
	}

	
}