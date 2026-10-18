package com.example.tv_track_backend.utils;

import org.springframework.security.oauth2.jwt.Jwt;

// Access the id of the currently logged in user from different places
public final class CurrentUser {

    private CurrentUser() {
    }

    public static Long id(Jwt jwt) {
        return Long.valueOf(jwt.getSubject());
    }
}
