package com.felipesouto.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipesouto.cursomc.domain.Cliente;
import com.felipesouto.cursomc.repositories.ClienteRepository;
import com.felipesouto.cursomc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(bcryptPasswordEncoder.encode(newPass));

		clienteRepository.save(cliente); /* salvando a nova senha no cliente */
		emailService.sendNewPasswordEmail(cliente, newPass); /* Enviar email cliente */
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);

		if (opt == 0){ //Gera um dígito
			return (char) (rand.nextInt(10)+48);
		}
		else if(opt ==1){ // Gera uma Letra Maiuscula
			return (char) (rand.nextInt(26)+65);
		}else{  // Gera uma Letra Minuscula
			return (char) (rand.nextInt(26)+97);
		}

	}

}
