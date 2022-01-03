package com.felipesouto.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.felipesouto.cursomc.domain.Cliente;
import com.felipesouto.cursomc.repositories.ClienteRepository;
import com.felipesouto.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:"+id+", Tipo:"+ Cliente.class.getName()));
	}
	
	

}
