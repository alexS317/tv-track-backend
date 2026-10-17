package com.example.tv_track_backend.auth.dto;

import com.example.tv_track_backend.user.dto.UserResponse;

public record AuthResponse(
        String token,
        UserResponse user
        ) {}
