package com.example.tv_track_backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.tv_track_backend.user.dto.ChangePasswordRequest;
import com.example.tv_track_backend.user.dto.UpdateUserRequest;
import com.example.tv_track_backend.user.dto.UserResponse;
import com.example.tv_track_backend.utils.CurrentUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponse me(@AuthenticationPrincipal Jwt jwt) {
        User user = userService.getById(CurrentUser.id(jwt));
        return UserResponse.from(user);
    }

    @PatchMapping
    public UserResponse update(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody UpdateUserRequest request) {
        User user = userService.updateUser(CurrentUser.id(jwt), request);

        return UserResponse.from(user);
    }

    @PatchMapping("/password")
    public void changePassword(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(CurrentUser.id(jwt), request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal Jwt jwt) {
        userService.deleteUser(CurrentUser.id(jwt));
    }
}
