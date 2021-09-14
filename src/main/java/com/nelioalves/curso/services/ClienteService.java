package com.nelioalves.curso.services;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.domain.Cliente;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private JpaRepository<Cliente, Integer> repo;

    public Cliente buscar(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(1, "Página não encontrada! "));
    }
}
