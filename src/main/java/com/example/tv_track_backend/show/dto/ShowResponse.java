package com.example.tv_track_backend.show.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.example.tv_track_backend.show.Show;
import com.example.tv_track_backend.show.ShowStatus;

public record ShowResponse(
        Long id,
        Long tvmazeId,
        ShowStatus status,
        Integer personalRating,
        LocalDate startedWatching,
        LocalDate finishedWatching,
        Long currentEpisodeId,
        Instant createdAt,
        Instant updatedAt
        ) {

    public static ShowResponse from(Show show) {
        return new ShowResponse(
                show.getId(),
                show.getTvmazeId(),
                show.getStatus(),
                show.getPersonalRating(),
                show.getStartedWatching(),
                show.getFinishedWatching(),
                show.getCurrentEpisodeId(),
                show.getCreatedAt(),
                show.getUpdatedAt()
        );
    }
}
