package com.assignment.mcb.mcbcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.assignment.mcb.*")
public class McbCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(McbCodeApplication.class, args);
	}

}
