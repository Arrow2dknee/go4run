package dev.antrosh.go4run.activities;

public record CreateActivityDto(
   String title,
   ActivityType type,
   Location location
) {}
