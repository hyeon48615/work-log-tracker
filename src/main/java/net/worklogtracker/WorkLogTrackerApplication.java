package net.worklogtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WorkLogTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkLogTrackerApplication.class, args);
    }

}
