package com.travelpartner.email_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class UserEmailConsumer {

	@Autowired
	private EmailServiceConfig emailServiceConfig;

	// Create a logger instance for logging
	private static final Logger logger = LogManager.getLogger(UserEmailConsumer.class);

	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void emailConsumer(Map<String, Object> message) {
		try {
			if (message != null && !message.isEmpty()) {

				logger.info("Received message: {}", message);

				String toEmailId = (String) message.get("toEmailId");

				String subject = (String) message.get("subject");

				String content = (String) message.get("content");

				logger.info("input params: {}", toEmailId, subject, content);

				String emailResponse = emailServiceConfig.sendNotification(toEmailId, subject, content);

				logger.info("Email response: {}", emailResponse);

			} else {
				logger.warn("Received invalid message: {}", message);
			}
		} catch (Exception e) {
			// Log any error that occurs during message processing
			logger.error("Error processing the Kafka message: {}", message, e);
		}
	}

}
