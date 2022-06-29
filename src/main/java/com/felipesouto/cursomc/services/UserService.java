package com.felipesouto.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.felipesouto.cursomc.security.UserSS;

public class UserService {
	//Retorna o Usuário que esta logado no Sistema
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			return null;
		}
	}
}
