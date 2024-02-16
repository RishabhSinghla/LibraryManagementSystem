package com.nagarro.webapp;

/**
 * @author rishabhsinghla
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AdvanceJavaAssignment5App2Application {

	public static void main(String[] args) {
		SpringApplication.run(AdvanceJavaAssignment5App2Application.class, args);

	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
