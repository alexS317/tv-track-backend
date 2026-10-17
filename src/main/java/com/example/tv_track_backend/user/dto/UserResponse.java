package com.example.tv_track_backend.user.dto;

import java.time.Instant;

import com.example.tv_track_backend.user.User;

public record UserResponse(
        Long id,
        String name,
        String email,
        Instant createdAt,
        Instant updatedAt
        ) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
