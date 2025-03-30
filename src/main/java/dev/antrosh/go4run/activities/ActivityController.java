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
    private final ActivityDBRepository _activityDbRepository;

    public ActivityController(ActivityRepository repository, ActivityDBRepository dbRepository) {
        this._activityRepository = repository;
        this._activityDbRepository = dbRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Activity> getAllActivities() {
        return _activityDbRepository.findAllActivities();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Integer id) {
        Optional<Activity> item = _activityDbRepository.findActivityById(id);

        if (item.isEmpty()) {
            throw new ActivityNotFoundException();
        }
        return item.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ActivityId startNewActivity(@Valid @RequestBody Activity activity) {
        _activityDbRepository.createActivity(activity);

        return new ActivityId(activity.id());
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Activity completeActivity(int id) {
        return _activityRepository.completeActivity(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateActivity(@Valid @RequestBody Activity activity, @PathVariable int id) {
        _activityDbRepository.updateActivity(activity, id);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable int id) {
        _activityDbRepository.deleteActivity(id);
    }
}
