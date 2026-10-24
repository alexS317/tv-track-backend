package com.example.tv_track_backend.show;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.tv_track_backend.show.dto.CreateShowRequest;
import com.example.tv_track_backend.show.dto.UpdateShowRequest;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getAllShows(Long userId) {
        return showRepository.findAllByUserId(userId);
    }

    public Show getShow(Long id, Long userId) {
        return findShow(id, userId);
    }

    @Transactional
    public Show addShow(Long userId, CreateShowRequest request) {
        if (showRepository.existsByUserIdAndTvmazeId(userId, request.tvmazeId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You already saved this show.");
        }

        Show show = showRepository.save(
                new Show(
                        userId,
                        request.tvmazeId(),
                        request.title(),
                        request.coverImage()));

        return show;
    }

    @Transactional
    public Show updateShow(Long id, Long userId, UpdateShowRequest request) {
        Show show = findShow(id, userId);

        if (request.startedWatching() != null && request.finishedWatching() != null
                && request.finishedWatching().isBefore(request.startedWatching())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Finish date must be after start date.");
        }

        show.setStatus(request.status());
        show.setPersonalRating(request.personalRating());
        show.setStartedWatching(request.startedWatching());
        show.setFinishedWatching(request.finishedWatching());
        show.setCurrentEpisodeId(request.currentEpisodeId());
        show.setCurrentSeason(request.currentSeason());
        show.setCurrentEpisode(request.currentEpisode());

        return show;
    }

    @Transactional
    public void deleteShow(Long id, Long userId) {
        Show show = findShow(id, userId);

        showRepository.delete(show);
    }

    private Show findShow(Long id, Long userId) {
        return showRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found."));
    }
}
