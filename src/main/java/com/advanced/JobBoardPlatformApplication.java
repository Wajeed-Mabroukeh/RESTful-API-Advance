package com.advanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class JobBoardPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBoardPlatformApplication.class, args);
	}

}
