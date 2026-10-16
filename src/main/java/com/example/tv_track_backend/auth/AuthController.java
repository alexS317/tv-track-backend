package com.example.tv_track_backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tv_track_backend.auth.dto.LoginRequest;
import com.example.tv_track_backend.auth.dto.RegisterRequest;
import com.example.tv_track_backend.user.User;
import com.example.tv_track_backend.user.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);

        // return new TokenResponse(request.password());
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @PostMapping("/login")
    public UserDto login(@Valid @RequestBody LoginRequest request) {
        User user = authService.login(request);

        // return new TokenResponse(request.password());
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
