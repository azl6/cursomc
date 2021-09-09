package com.nelioalves.curso.repositories;

import com.nelioalves.curso.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
