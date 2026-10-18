package com.example.tv_track_backend.show.dto;

import java.time.LocalDate;

import com.example.tv_track_backend.show.ShowStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateShowRequest(
        @NotNull
        ShowStatus status,
        @Min(1)
        @Max(10)
        Integer personalRating,
        LocalDate startedWatching,
        LocalDate finishedWatching,
        Long currentEpisodeId
        ) {

}
