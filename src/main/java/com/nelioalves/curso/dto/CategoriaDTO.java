package com.nelioalves.curso.dto;

import com.nelioalves.curso.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private int id;
    private String nome;

    public CategoriaDTO(){
    }
    public CategoriaDTO(Categoria obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
