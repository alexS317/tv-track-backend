package com.example.tv_track_backend.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.tv_track_backend.auth.dto.AuthResponse;
import com.example.tv_track_backend.auth.dto.LoginRequest;
import com.example.tv_track_backend.auth.dto.RegisterRequest;
import com.example.tv_track_backend.user.User;
import com.example.tv_track_backend.user.UserRepository;
import com.example.tv_track_backend.user.UserResponse;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (!request.password().equals(request.passwordConfirmation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Password and password confirmation do not match.");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This email is already registered.");
        }

        User user = userRepository.save(
                new User(
                        request.name(),
                        request.email(),
                        passwordEncoder.encode(request.password())
                )
        );

        return new AuthResponse(jwtService.issueToken(user), UserResponse.from(user));
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication;

        // Use authenticationManager defined in SecurityConfig
        try {
            authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(
                            request.email(), request.password()
                    )
            );
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Invalid credentials.");
        }

        // after authentication getPrincipal returns result of loadUserbyUsername -> UserDetails object is cast to User
        User user = (User) authentication.getPrincipal();

        return new AuthResponse(jwtService.issueToken(user), UserResponse.from(user));
    }
}
