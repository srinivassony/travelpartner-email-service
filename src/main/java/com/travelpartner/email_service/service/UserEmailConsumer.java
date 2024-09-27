package com.travelpartner.email_service.service;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserEmailConsumer {

	@PostConstruct
	public void init() {
		System.out.println("UserEmailConsumer started...");
	}

	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void UserConsumer(Map<String, Object> message) {
		System.out.println("message" + " " + message.get("name"));
	}

}
