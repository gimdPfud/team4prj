package com.example.project4team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Project4teamApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project4teamApplication.class, args);
    }

}
