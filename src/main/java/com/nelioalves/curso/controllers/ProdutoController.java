package com.nelioalves.curso.controllers;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.domain.Produto;
import com.nelioalves.curso.dto.CategoriaDTO;
import com.nelioalves.curso.dto.ProdutoDTO;
import com.nelioalves.curso.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto obj = produtoService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
                                                     @RequestParam(value = "nome", defaultValue = "") Integer nome,
                                                     @RequestParam(value = "categorias", defaultValue = "") Integer categorias,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC")String direction){
        Page<Produto> list = service.search(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }


}
