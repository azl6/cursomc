package com.nelioalves.curso.controllers;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.dto.CategoriaDTO;
import com.nelioalves.curso.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @ApiOperation(value="Busca por id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);
        }

        @ApiOperation(value="Insere categoria")
        @PreAuthorize("hasAnyRole('ADMIN')")
        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
            Categoria obj = service.fromDto(objDto);
            obj = service.insert(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        //o VOID do responseEntity é pq a requisição retorna um corpo vazio
        //por isso o .nocontent.build
        @ApiOperation(value="Atualiza categoria")
        @PreAuthorize("hasAnyRole('ADMIN')")
        @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
        Categoria obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
        }

    @ApiOperation(value="Remove categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos"),
            @ApiResponse(code = 404, message = "Código inexistente") })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value="Retorna todas categorias")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
        }

    @ApiOperation(value="Retorna todas categorias com paginação")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC")String direction){
        Page<Categoria> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}








