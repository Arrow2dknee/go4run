package dev.antrosh.go4run.activities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException() {
        super("Activity does not exists");
    }
}
