package com.felipesouto.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipesouto.cursomc.domain.Categoria;
import com.felipesouto.cursomc.domain.Produto;
import com.felipesouto.cursomc.repositories.CategoriaRepository;
import com.felipesouto.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 Categoria cat1 = new Categoria(null,"Escritorio 1");
		 Categoria cat2 = new Categoria(null, "Informatica 2");
		 
		 
		 Produto p1 = new Produto(null, "Computador", 4.000);
		 Produto p2 = new Produto(null, "Impressora", 200.0);
		 Produto p3 = new Produto(null, "Mouse", 40.0);
		 
		 
		 cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		 cat2.getProdutos().addAll(Arrays.asList(p2));
		 
		 
		 p1.getCategorias().addAll(Arrays.asList(cat1));
		 p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		 p3.getCategorias().addAll(Arrays.asList(cat1));
		 
		 categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		 produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		 
		 
		
	}

}
