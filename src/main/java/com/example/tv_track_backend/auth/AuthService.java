package com.example.tv_track_backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.tv_track_backend.auth.dto.LoginRequest;
import com.example.tv_track_backend.auth.dto.RegisterRequest;
import com.example.tv_track_backend.user.User;
import com.example.tv_track_backend.user.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        if (!request.password().equals(request.passwordConfirmation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and password confirmation do not match.");
        }

        if (userRepository.existsByEmail(request.email().trim().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already registered.");
        }

        User user = new User(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password())
        );

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with this email was found.")
        );

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials.");
        }

        return user;
    }
}
