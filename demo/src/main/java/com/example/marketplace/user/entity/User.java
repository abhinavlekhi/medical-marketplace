package com.example.marketplace.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Getter
@Setter
@NoArgsConstructor()
@AllArgsConstructor()
@Builder //lets you create objects cleanly using builder pattern, but why builder pattern?? because it helps in creating immutable objects and provides clear, readable way to construct complex objects step by step.
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String userName;

    @Column(nullable = false, name = "password_hash")
    private String passwordHash;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

}
