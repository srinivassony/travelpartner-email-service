package com.travelpartner.email_service.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceConfig {

	// Create a logger instance for logging
	private static final Logger logger = LogManager.getLogger(EmailServiceConfig.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public String sendNotification(String toEmailId, String subject, String message) {

		// Try block to check for exceptions
		try {

			// Creating a MIME message
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			// Creating a MimeMessageHelper to handle the message
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

			// Setting up necessary details
			messageHelper.setFrom(sender);
			messageHelper.setTo("srinivas.sony777@gmail.com");
			messageHelper.setSubject(subject);
			messageHelper.setText(message, true); // Set 'true' to indicate HTML content

			// Sending the mail
			javaMailSender.send(mimeMessage);

			logger.info("Email input params: {}", mimeMessage);

			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {

			logger.error("Error processing the email: {}", e);
			return "Error while Sending Mail";
		}
	}

}
