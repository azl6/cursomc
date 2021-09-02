package com.nelioalves.curso.repositories;

import com.nelioalves.curso.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//aula 17
//terminar 22:00 aula 17
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
