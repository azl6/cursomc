package com.nelioalves.curso.repositories;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("\"SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE \n" +
            "obj.nome LIKE %:nome% AND cat IN :categorias\")\n")
    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
