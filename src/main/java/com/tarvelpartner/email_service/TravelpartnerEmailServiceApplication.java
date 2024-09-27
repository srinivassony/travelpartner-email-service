package com.tarvelpartner.email_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.travelpartner.email_service.service")
public class TravelpartnerEmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelpartnerEmailServiceApplication.class, args);
		
		System.out.println("Application started!");
	}

}
