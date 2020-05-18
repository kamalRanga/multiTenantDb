package com.uno.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendEmail(String template) {
		System.out.println("Email sent succesfully");
	}

}
