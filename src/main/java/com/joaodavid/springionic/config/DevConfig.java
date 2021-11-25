package com.joaodavid.springionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.joaodavid.springionic.service.DBService;
import com.joaodavid.springionic.service.EmailService;
import com.joaodavid.springionic.service.MockEmailService;
import com.joaodavid.springionic.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean InstantiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.InstantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
