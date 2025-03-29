package dev.antrosh.go4run.activities;

import java.time.LocalDateTime;

public record Activity(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer durationInMinutes,
        ActivityType type,
        Location location
) {}
