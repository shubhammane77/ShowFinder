package com.showFinder.showFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.showFinder"})
public class ShowFinderApplication {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.home"));

		SpringApplication.run(ShowFinderApplication.class, args);
	}

}
