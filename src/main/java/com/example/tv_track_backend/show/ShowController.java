package com.example.tv_track_backend.show;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.tv_track_backend.show.dto.CreateShowRequest;
import com.example.tv_track_backend.show.dto.ShowResponse;
import com.example.tv_track_backend.show.dto.UpdateShowRequest;
import com.example.tv_track_backend.utils.CurrentUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {

    private final ShowService showService;

    ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public List<ShowResponse> index(@AuthenticationPrincipal Jwt jwt) {
        List<Show> shows = showService.getAllShows(CurrentUser.id(jwt));

        return shows.stream().map(ShowResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ShowResponse show(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        Show show = showService.getShow(id, CurrentUser.id(jwt));

        return ShowResponse.from(show);
    }

    @PostMapping
    public ShowResponse create(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateShowRequest request) {
        Show show = showService.addShow(
                CurrentUser.id(jwt),
                request);

        return ShowResponse.from(show);
    }

    @PatchMapping("/{id}")
    public ShowResponse update(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id, @Valid @RequestBody UpdateShowRequest request) {
        Show show = showService.updateShow(id, CurrentUser.id(jwt), request);

        return ShowResponse.from(show);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        showService.deleteShow(id, CurrentUser.id(jwt));
    }

}
