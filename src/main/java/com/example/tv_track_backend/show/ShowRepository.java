package com.example.tv_track_backend.show;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findAllByUserId(Long userId);

    Optional<Show> findByIdAndUserId(Long id, Long userId);

    Boolean existsByUserIdAndTvmazeId(Long userId, Long tvmazeId);
}
