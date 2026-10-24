package com.example.tv_track_backend.show.dto;

import jakarta.validation.constraints.NotNull;

public record CreateShowRequest(
        @NotNull
        Long tvmazeId,
        @NotNull
        String title,
        String coverImage
        ) {

}
