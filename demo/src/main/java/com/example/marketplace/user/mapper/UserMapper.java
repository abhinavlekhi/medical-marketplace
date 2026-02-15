package com.example.marketplace.user.mapper;

import com.example.marketplace.user.dto.UserResponse;
import com.example.marketplace.user.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getCreatedAt(),
                user.getUserName()
        );
    }
}
