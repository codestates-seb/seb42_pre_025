package com.stackoverflow.team25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Team25Application {

	public static void main(String[] args) {
		SpringApplication.run(Team25Application.class, args);
	}

}
