package com.felipesouto.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipesouto.cursomc.domain.Categoria;
import com.felipesouto.cursomc.repositories.CategoriaRepository;
import com.felipesouto.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:"+id+", Tipo:"+ Categoria.class.getName()));
	}

}
