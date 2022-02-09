package com.felipesouto.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.felipesouto.cursomc.domain.Pedido;

public interface EmailService {

	
	void sendOrderConfirmationEmail(Pedido obj);
	
	
	void sendEmail(SimpleMailMessage  msg);
}
