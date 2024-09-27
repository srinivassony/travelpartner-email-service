package com.travelpartner.email_service.service;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class UserEmailConsumer {

	// Create a logger instance for logging
	private static final Logger logger = LogManager.getLogger(UserEmailConsumer.class);

	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void UserInvitationEmailConsumer(Map<String, Object> message) {
		try {
			if (message != null && message.size()>0) {
				String name = (String) message.get("name");
				logger.info("Received message with user name: {}", name);

				// Simulate message processing
				logger.info("Processing user message for: {}", name);
			} else {
				logger.warn("Received invalid message: {}", message);
			}
		} catch (Exception e) {
			// Log any error that occurs during message processing
			logger.error("Error processing the Kafka message: {}", message, e);
		}
	}

}
