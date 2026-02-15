package com.example.marketplace.user.service;

import com.example.marketplace.common.exception.DuplicateUsernameException;
import com.example.marketplace.user.entity.User;
import com.example.marketplace.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String passwordhash) {
        if(userRepository.existsByUserName(username)) {
            throw new DuplicateUsernameException("Username already exists");
        }
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserName(username);
        user.setPasswordHash(passwordhash);
        user.setCreatedAt(OffsetDateTime.now().toInstant());
        return userRepository.save(user);
    }
}
