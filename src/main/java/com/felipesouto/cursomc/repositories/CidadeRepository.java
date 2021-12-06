package com.felipesouto.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipesouto.cursomc.domain.Cidade;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
