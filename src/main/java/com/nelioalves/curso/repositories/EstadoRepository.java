package com.nelioalves.curso.repositories;

import com.nelioalves.curso.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Transactional(readOnly=true)
    public List<Estado> findAllByOrderByNome();
}
