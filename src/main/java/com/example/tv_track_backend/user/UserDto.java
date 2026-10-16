package com.example.tv_track_backend.user;

import java.time.Instant;

import jakarta.validation.constraints.Email;

public record UserDto(
        Long id,
        String name,
        @Email
        String email,
        Instant createdAt,
        Instant updatedAt
        ) {}
