package com.nelioalves.curso.domain;

import java.util.Objects;

public class Categoria {
    //criar categoria nos padrões
    //trocar lá na função pra retornar a lista de 2 categorias: info e escritório
    //testar no postman a url localhost:8080/categorias

    private int id;
    private String nome;

    public Categoria(){
    }

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
