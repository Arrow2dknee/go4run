package dev.antrosh.go4run.activities;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Activity> findById(Integer id) {
        return activityList.stream()
                .filter(activity -> activity.id().equals(id))
                .findFirst();
    }

    public Integer createActivity(CreateActivityDto activity) {
        Integer newActivityId = activityList.size() + 1;
        activityList.add(new Activity(
                newActivityId,
                activity.title(),
                LocalDateTime.now(),
                null,
                null,
                activity.type(),
                activity.location()
        ));

        return newActivityId;
    }

    public Activity completeActivity(int id) {
        Optional<Activity> item = findById(id);

        if (item.isPresent()) {
            LocalDateTime now = LocalDateTime.now();

            Activity activityToUpdate = item.get();
            // Insert code below to perform setter functionality
        }
        return item.get();
    }

    public void updateActivity(Activity activity, int id) {
        Optional<Activity> existingActivity = findById(id);

        existingActivity.ifPresent(value -> activityList.set(activityList.indexOf(value), activity));
    }

    public void deleteActivity(int id) {
        activityList.removeIf(activity -> activity.id().equals(id));
    }
}
