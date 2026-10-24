package com.example.tv_track_backend.show;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shows")
@EntityListeners(AuditingEntityListener.class)
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "tvmaze_id", nullable = false, updatable = false)
    private Long tvmazeId;

    @Column(nullable = false)
    private String title;

    private String coverImage;

    @Enumerated(EnumType.STRING)
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private ShowStatus status;

    private Integer personalRating;
    private LocalDate startedWatching;
    private LocalDate finishedWatching;
    private Long currentEpisodeId;
    private Integer currentSeason;
    private Integer currentEpisode;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Show() {
    }

    public Show(Long userId, Long tvmazeId, String title, String coverImage) {
        this.userId = userId;
        this.tvmazeId = tvmazeId;
        this.title = title;
        this.coverImage = coverImage;
        this.status = ShowStatus.PLANNED;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTvmazeId() {
        return tvmazeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public ShowStatus getStatus() {
        return status;
    }

    public void setStatus(ShowStatus status) {
        this.status = status;
    }

    public Integer getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(Integer personalRating) {
        this.personalRating = personalRating;
    }

    public LocalDate getStartedWatching() {
        return startedWatching;
    }

    public void setStartedWatching(LocalDate startedWatching) {
        this.startedWatching = startedWatching;
    }

    public LocalDate getFinishedWatching() {
        return finishedWatching;
    }

    public void setFinishedWatching(LocalDate finishedWatching) {
        this.finishedWatching = finishedWatching;
    }

    public Long getCurrentEpisodeId() {
        return currentEpisodeId;
    }

    public void setCurrentEpisodeId(Long currentEpisodeId) {
        this.currentEpisodeId = currentEpisodeId;
    }

    public Integer getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Integer currentSeason) {
        this.currentSeason = currentSeason;
    }

    public Integer getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(Integer currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
