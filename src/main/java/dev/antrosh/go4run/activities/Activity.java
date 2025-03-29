package dev.antrosh.go4run.activities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Activity(
        Integer id,
        @NotEmpty // validation constraints
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer durationInMinutes,
        ActivityType type,
        Location location
) {
    public Activity {
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("CompletedOn must be after startedOn");
        }
    }
}
