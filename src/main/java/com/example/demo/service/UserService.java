package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface UserService {
    User register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    User findByEmail(String email);
    User findById(Long id);
}