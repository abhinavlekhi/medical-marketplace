package com.example.marketplace.user.controller;

import com.example.marketplace.common.exception.InvalidCredentialsException;
import com.example.marketplace.user.dto.LoginRequest;
import com.example.marketplace.user.dto.LoginResponse;
import com.example.marketplace.user.entity.User;
import com.example.marketplace.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        User user = userRepository.findByUserName(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash());

        if( !matches ) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return ResponseEntity.ok(new LoginResponse("Login successful", user.getUserName()));
    }
}
