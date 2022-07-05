package com.felipesouto.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipesouto.cursomc.domain.Estado;
import com.felipesouto.cursomc.dto.EstadoDTO;
import com.felipesouto.cursomc.repositories.EstadoRepository;
import com.felipesouto.cursomc.services.exception.DataIntegrityViolationException;
import com.felipesouto.cursomc.services.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado find(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:" + id + ", Tipo:" + Estado.class.getName()));
	}

	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}

	public Estado insert(Estado obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Estado update(Estado obj) {
		Estado newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	
	
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir esse Estado");
		}
	}
	
	

	public Page<Estado> findPage(
			Integer page,
			Integer linesPerPage,
			String orderBy,
			String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	

	public Estado fromDTO(EstadoDTO objDto) {
		return new Estado(objDto.getId(), objDto.getNome());
	}

	private void updateData(Estado newObj, Estado obj) {
		newObj.setNome(obj.getNome());
	}

}
