package com.nelioalves.curso.repositories;

import com.nelioalves.curso.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
