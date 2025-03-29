package dev.antrosh.go4run.activities;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityRepository {
    private final List<Activity> activityList = new ArrayList<>();

    @PostConstruct
    private void init() {
        activityList.add(new Activity(
                1,
                "Weekend long distance run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                60,
                ActivityType.Running,
                Location.Outdoor
        ));

        activityList.add(new Activity(
                2,
                "Weekend badminton with friends",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(25),
                25,
                ActivityType.Badminton,
                Location.Indoor
        ));
    }

    public List<Activity> findAll() {
        return activityList;
    }
}
