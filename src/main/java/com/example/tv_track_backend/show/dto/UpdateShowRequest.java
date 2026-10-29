package com.example.tv_track_backend.show.dto;

import java.time.LocalDate;

import org.openapitools.jackson.nullable.JsonNullable;

import com.example.tv_track_backend.show.ShowStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateShowRequest(
        @NotNull
        ShowStatus status,
        JsonNullable<@Min(1) @Max(10) Integer> personalRating,
        JsonNullable<LocalDate> startedWatching,
        JsonNullable<LocalDate> finishedWatching,
        JsonNullable<Long> currentEpisodeId,
        JsonNullable<Integer> currentSeason,
        JsonNullable<Integer> currentEpisode
        ) {

}
