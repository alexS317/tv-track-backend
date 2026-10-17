package com.example.tv_track_backend.user.dto;

import java.util.Locale;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank
        @Size(max = 255)
        String name,
        @NotBlank
        @Email
        @Size(max = 255)
        String email
        ) {

    public UpdateUserRequest {
        name = name == null ? null : name.strip();
        email = email == null ? null : email.strip().toLowerCase(Locale.ROOT);
    }
}
