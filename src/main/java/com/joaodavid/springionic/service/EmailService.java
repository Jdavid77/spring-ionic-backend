package com.joaodavid.springionic.service;

import org.springframework.mail.SimpleMailMessage;

import com.joaodavid.springionic.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
