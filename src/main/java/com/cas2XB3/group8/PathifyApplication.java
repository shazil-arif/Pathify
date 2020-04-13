/**
 * @brief Spring boot application runner
 * @author Group8
 * @file PathifyApplication.java
 * @date April 10th 2020
 */

package com.cas2XB3.group8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @brief Main Runner for the Spring Boot Application
 */ 
@SpringBootApplication
public class PathifyApplication {

	/**
	 * @brief main runner for the spring boot application
	 * @param args command line arguments (if any)
	 */
	public static void main(String[] args) {
		SpringApplication.run(PathifyController.class, args);
	}

}
