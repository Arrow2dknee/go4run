package dev.antrosh.go4run.activities;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityRepository _activityRepository;

    public ActivityController(ActivityRepository repository) {
        _activityRepository = repository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Activity> getAllActivities() {
        return _activityRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Integer id) {
        Optional<Activity> item = _activityRepository.findById(id);

        if (item.isEmpty()) {
            throw new ActivityNotFoundException();
        }
        return item.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ActivityId startNewActivity(@Valid @RequestBody CreateActivityDto activity) {
        int activityId = _activityRepository.createActivity(activity);

        return new ActivityId(activityId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Activity completeActivity(int id) {
        return _activityRepository.completeActivity(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateActivity(@Valid @RequestBody Activity activity, @PathVariable int id) {
        _activityRepository.updateActivity(activity, id);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable int id) {
        _activityRepository.deleteActivity(id);
    }

    @GetMapping()
    String homePage() {
        return "Welcome to your activity dashboard";
    }
}
