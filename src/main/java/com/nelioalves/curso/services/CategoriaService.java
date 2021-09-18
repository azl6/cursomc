package com.nelioalves.curso.services;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.repositories.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

//aula 17
@Service
public class CategoriaService {

    @Autowired
    private JpaRepository<Categoria, Integer> repo;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(1, "Página não encontrada!"));
    }

    public Categoria insert(Categoria obj){
        return repo.save(obj);
    }
}
