package dev.antrosh.go4run;

import dev.antrosh.go4run.activities.Activity;
import dev.antrosh.go4run.activities.ActivityType;
import dev.antrosh.go4run.activities.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Go4runApplication {

	private static final Logger _logger = LoggerFactory.getLogger(Go4runApplication.class);

	public static void main(String[] args) {
		// Code below is to load all classes from application context container
		// ConfigurableApplicationContext cxt = SpringApplication.run(Go4runApplication.class, args);
		// WelcomeMessage welcomeMessage = cxt.getBean("welcomeMessage"); // provide class name from the application context container
		// System.out.println(welcomeMessage);

		SpringApplication.run(Go4runApplication.class, args);
		_logger.info("Application started successfully");
	}

	@Bean()
	CommandLineRunner runner() {
		return args -> {
			Activity activity = new Activity(
					1,
					"Morning run",
					LocalDateTime.now(),
					LocalDateTime.now().plusMinutes(30),
					10,
					ActivityType.Jogging,
					Location.Outdoor
			);
            _logger.info("New activity found: {}", activity);
		};
	}
}
