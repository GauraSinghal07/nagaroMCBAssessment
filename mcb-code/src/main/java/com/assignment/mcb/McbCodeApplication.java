package com.assignment.mcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.assignment.mcb.*")
@EnableJpaRepositories(basePackages = "com.assignment.mcb.repository")
@EnableScheduling
public class McbCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(McbCodeApplication.class, args);
	}

}
