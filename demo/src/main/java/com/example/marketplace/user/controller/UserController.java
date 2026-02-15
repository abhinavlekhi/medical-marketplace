package com.example.marketplace.user.controller;

import com.example.marketplace.user.dto.RegisterRequest;
import com.example.marketplace.user.dto.UserResponse;
import com.example.marketplace.user.entity.User;
import com.example.marketplace.user.mapper.UserMapper;
import com.example.marketplace.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    // constructor injection for service layer, we could have used field injection i.e @Autowired but
    // constructor injection is generally preferred as it makes the class easier to test and promotes immutability.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // the flow Client -> Controller -> Service -> Entity
    // Entity -> mapped to UserResponse DTO -> Spring serializes it to JSON -> Controller returns response to client
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = userService.createUser(
                registerRequest.getUsername(),
                registerRequest.getPassword()
        );
        return UserMapper.toResponse(user);
    }
}
