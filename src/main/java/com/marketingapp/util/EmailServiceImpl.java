package com.marketingapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender  JavaMailSender ;

	@Override
	public void SendEmail(String To, String Subject, String Message) {
	 SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(To);
		message.setSubject(Subject);
		message.setText(Message);
		JavaMailSender.send(message);
		
		
	}

}
