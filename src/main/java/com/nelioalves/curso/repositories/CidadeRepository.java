package com.nelioalves.curso.repositories;

import com.nelioalves.curso.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
