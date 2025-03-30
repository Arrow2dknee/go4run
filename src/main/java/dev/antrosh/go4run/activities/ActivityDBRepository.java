package dev.antrosh.go4run.activities;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class ActivityDBRepository {
    private final JdbcClient _jdbcClient;

    public ActivityDBRepository(JdbcClient connection) {
        this._jdbcClient = connection;
    }

    public List<Activity> findAllActivities() {
        return _jdbcClient.sql("SELECT * FROM activity")
                .query(Activity.class)
                .list();
    }

    public Optional<Activity> findActivityById(int id) {
        return _jdbcClient.sql("SELECT * FROM activity WHERE id = :id")
                .param("id", id)
                .query(Activity.class)
                .optional();
    }

    public void createActivity(Activity a) {
        var created = _jdbcClient.sql("INSERT INTO activity(id,title,started_on,completed_on,duration_in_minutes,activity_type,activity_location) VALUES(?,?,?,?,?,?,?)")
                .params(List.of(a.id(), a.title(), a.startedOn(), a.completedOn(), a.durationInMinutes(), a.activityType().toString(), a.activityLocation().toString()))
                .update();
        Assert.state(created == 1, "Failed to create activity " + a.title());
    }

    public void updateActivity(Activity a, int id) {
        var updated = _jdbcClient.sql("UPDATE activity SET title=?,started_on=?,completed_on=?,duration_in_minutes=?,activity_type=?,activity_location=? WHERE id = ?")
                .params(List.of(a.title(), a.startedOn(), a.completedOn(), a.durationInMinutes(), a.activityType().toString(), a.activityLocation().toString(), id))
                .update();
        Assert.state(updated == 1, "Failed to update activity " + a.title());
    }

    public void deleteActivity(int id) {
        var updated = _jdbcClient.sql("DELETE FROM activity WHERE id = :id")
                .param("id", id)
                .update();
        Assert.state(updated == 1, "Failed to delete activity " + id);
    }

    public int activitiesCount() {
        return _jdbcClient.sql("SELECT * FROM activity").query().listOfRows().size();
    }

    public void saveAll(List<Activity> activities) {
        activities.forEach(this::createActivity);
    }

    public List<Activity> findByLocation(String location) {
        return _jdbcClient.sql("SELECT * FROM activity WHERE location = :location")
                .param("location", location)
                .query(Activity.class)
                .list();
    }
}
