package com.example.tv_track_backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.tv_track_backend.user.dto.ChangePasswordRequest;
import com.example.tv_track_backend.user.dto.UpdateUserRequest;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    public User getById(Long id) {
        return findUser(id);
    }

    @Transactional
    public User updateUser(Long id, UpdateUserRequest request) {
        User user = findUser(id);

        // Can't update email if someone else already uses it
        if (!request.email().equals(user.getEmail()) && userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This email is already registered.");
        }

        user.setName(request.name());
        user.setEmail(request.email());

        return user;
    }

    @Transactional
    public void changePassword(Long id, ChangePasswordRequest request) {
        if (request.newPassword().equals(request.currentPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "New password cannot be identical with the current password.");
        }

        if (!request.newPassword().equals(request.newPasswordConfirmation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Password and password confirmation do not match.");
        }

        User user = findUser(id);

        // Can only update with valid current password
        if (request.currentPassword() == null
                || !passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Current password is incorrect.");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findUser(id);

        userRepository.delete(user);
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
        );
    }
}
