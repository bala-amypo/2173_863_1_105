package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // check duplicate username
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // encrypt password
        user.setPassword(encoder.encode(user.getPassword()));

        // default role if not provided
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        userRepository.save(user);

        return "User registered successfully";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(
                dbUser.getUsername(),
                dbUser.getRole(),
                dbUser.getId(),
                dbUser.getEmail()
        );
    }
}
