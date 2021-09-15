package com.nelioalves.curso.repositories;

import com.nelioalves.curso.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
