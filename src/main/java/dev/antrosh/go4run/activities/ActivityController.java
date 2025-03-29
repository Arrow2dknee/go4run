package dev.antrosh.go4run.activities;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityRepository _activityRepository;

    public ActivityController(ActivityRepository repository) {
        _activityRepository = repository;
    }

    @GetMapping("/all")
    public List<Activity> getAllActivities() {
        return _activityRepository.findAll();
    }

    @GetMapping()
    String homePage() {
        return "Welcome to your activity dashboard";
    }
}
