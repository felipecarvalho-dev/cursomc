package com.felipesouto.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felipesouto.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min =4,max =30, message="Tamanho deve ser entre 4 a 30 caracteres")
	private String nome;

	public CidadeDTO(){
	}
	
	
	public CidadeDTO(Cidade obj) {
		this.id=obj.getId();
		this.nome=obj.getNome();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
}
