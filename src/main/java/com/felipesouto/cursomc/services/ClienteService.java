package com.felipesouto.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.felipesouto.cursomc.domain.Cidade;
import com.felipesouto.cursomc.domain.Cliente;
import com.felipesouto.cursomc.domain.Endereco;
import com.felipesouto.cursomc.domain.enums.TipoCliente;
import com.felipesouto.cursomc.dto.ClienteDTO;
import com.felipesouto.cursomc.dto.ClienteNewDTO;
import com.felipesouto.cursomc.repositories.ClienteRepository;
import com.felipesouto.cursomc.repositories.EnderecoRepository;
import com.felipesouto.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:"+id+", Tipo:"+ Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj){
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	

	public  Cliente update(Cliente obj) {
		Cliente newOb = find(obj.getId());
		updateData(newOb, obj);
		return repo.save(newOb); 
	}
	
	public void delete(Integer id){
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
		  throw new com.felipesouto.cursomc.services.exception.DataIntegrityViolationException("Não é possível excluir um Cliente que possui Pedido");

	}
	}
	
	
	public List<Cliente> findAll(){
		return repo.findAll();
	} 
	
	
	public Page<Cliente> findPage(
			Integer page,
			Integer linesPerPage,
			String orderBy,
			String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	
	
	public Cliente fromDTO(ClienteDTO objDto){
		return new Cliente(objDto.getId(), objDto.getNome(),objDto.getEmail(), null,null,null);
	}
	

	public Cliente fromDTO(ClienteNewDTO objDto){
		Cliente  cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()),pe.encode(objDto.getSenha()));
		Cidade   cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj){
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
